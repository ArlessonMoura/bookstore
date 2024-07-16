package br.com.oraclechallenge.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Result> results;
}

