package br.com.oraclechallenge.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    private String name;
    @JsonProperty("birth_year")
    private int birthYear;
    @JsonProperty("death_year")
    private int deathYear;
}
