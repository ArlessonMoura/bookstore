package br.com.oraclechallenge.bookstore.service;

import br.com.oraclechallenge.bookstore.dto.GutendexResponse;
import br.com.oraclechallenge.bookstore.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String GUTENDEX_API_URL = "https://gutendex.com/books/";

    public List<Book> searchBooks(String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GUTENDEX_API_URL)
                .queryParam("search", query);

        GutendexResponse response = restTemplate.getForObject(builder.toUriString(), GutendexResponse.class);

        return response != null ? response.getResults() : Collections.emptyList();
    }
}
