package br.com.oraclechallenge.bookstore.service;

import br.com.oraclechallenge.bookstore.dto.GutendexResponse;
import br.com.oraclechallenge.bookstore.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Service
public class GutendexService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String GUTENDEX_API_URL = "https://gutendex.com/books/";

    public GutendexService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Book> searchBooks(String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", query);

        String url = builder.toUriString();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(createUri(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            GutendexResponse gutendexResponse = processResponseBody(response.body());
            return gutendexResponse != null ? gutendexResponse.getResults() : Collections.emptyList();
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

    private GutendexResponse processResponseBody(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, GutendexResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing JSON response from Gutendex API", e);
        }
    }
}
