package br.com.oraclechallenge.bookstore.dto;

import br.com.oraclechallenge.bookstore.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponse(
//        Map<String, Integer> count,
//        Map<String, Integer> next,
//        Map<String, Integer> previous,
        LinkedHashMap<String, Book> results,
        List<String> translators,
        List<String> subjects,
        List<String> bookshelves,
        List<String> languages

) {
}