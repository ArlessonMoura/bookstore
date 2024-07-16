package br.com.oraclechallenge.bookstore.dto;

import br.com.oraclechallenge.bookstore.model.Book;

import java.util.List;

public class GutendexResponse {

    public GutendexResponse() {
    }

    private List<Book> results;

    public List<Book> getResults() {
        return results;
    }
}