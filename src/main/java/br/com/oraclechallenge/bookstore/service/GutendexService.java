package br.com.oraclechallenge.bookstore.service;

import br.com.oraclechallenge.bookstore.dto.GutendexResponse;
import br.com.oraclechallenge.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final String GUTENDEX_API_URL;

    public GutendexService(RestTemplate restTemplate, @Value("${gutendex.api.url}") String gutendexApiUrl) {
        this.restTemplate = restTemplate;
        this.GUTENDEX_API_URL = gutendexApiUrl;
    }

    public List<Book> searchBooks(String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", query);

        String url = builder.toUriString();
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        return response != null ? response.getResults() : List.of();
    }
}
