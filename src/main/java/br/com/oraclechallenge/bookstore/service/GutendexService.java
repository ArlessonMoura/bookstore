package br.com.oraclechallenge.bookstore.service;

import br.com.oraclechallenge.bookstore.dto.GutendexResponse;
import br.com.oraclechallenge.bookstore.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Book> searchBooks(String query) {
        String GUTENDEX_API_URL = "https://gutendex.com/books/";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", query);

        String url = builder.toUriString();
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        return response != null ? response.getResults() : List.of();
    }
}
