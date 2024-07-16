package br.com.oraclechallenge.bookstore.dto;

import br.com.oraclechallenge.bookstore.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexDTO(String title, List<Person> authors, List<String> languages) {


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Authors: \n");
        for (Person author : authors) {
            sb.append("  - ").append(author.getAuthor()).append("\n");
        }
        sb.append("Languages: ").append(String.join(", ", languages)).append("\n");
        sb.append("----------------------------------------");
        return sb.toString();
    }
}