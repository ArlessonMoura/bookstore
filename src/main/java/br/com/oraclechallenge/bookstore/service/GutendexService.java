package br.com.oraclechallenge.bookstore.service;

import br.com.oraclechallenge.bookstore.dto.GutendexDTO;
import br.com.oraclechallenge.bookstore.model.Book;
import br.com.oraclechallenge.bookstore.repository.AuthorRepository;
import br.com.oraclechallenge.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String GUTENDEX_API_URL = "https://gutendex.com/books/";
    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public GutendexService(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void searchBooks(String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", query);

        String url = builder.toUriString();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(createUri(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            List<GutendexDTO> gutendexResponse = processResponseBody(response.body(), query);


            gutendexResponse.forEach(book -> {
                System.out.println(book.toString());
            });

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while fetching data from Gutendex API", e);
        }
    }

    private URI createUri(String url) {
        try {
            return new URI(url);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid URL: " + url, e);
        }
    }

    private void saveBooks(List<Book> books) {
        books.forEach(book ->{
            authorRepository.save(book.getAuthors());
            repository.save(book);
        });
    }

    private List<GutendexDTO> processResponseBody(String responseBody, String title) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode resultsNode = rootNode.path("results");
            if (resultsNode.isEmpty()) {
                throw new NoResultException("No books found for the given query.");
            }

            List<GutendexDTO> gutendexList = objectMapper
                    .readerForListOf(GutendexDTO.class)
                    .readValue(resultsNode);

            List<Book> avaliableBooks = repository.findByTitleContaining(title);
            
            if (!avaliableBooks.isEmpty()) {
                System.out.println("Removendo livros duplicados já existentes no banco de dados...");
                for (Book avaliableBook : avaliableBooks) {
                    gutendexList.removeIf(book -> avaliableBook.getTitle().equals(book.title()));
                }
            }

            if (!gutendexList.isEmpty()) {
                System.out.println("Salvando novos livros encontrados...");
                List<Book> novosLivros = gutendexList.stream().map(Book::new).collect(Collectors.toList());
                saveBooks(novosLivros);
                System.out.println("Livros salvos com sucesso!");
                return  gutendexList;
            } else {
                System.out.println("Todos os livros já estão registrados no banco de dados.");
                return null;
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error parsing JSON response from Gutendex API", e);
        }
    }
}
