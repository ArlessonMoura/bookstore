package br.com.oraclechallenge.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
//    private int id;
    private String title;
    private List<Author> authors;
//    private List<String> translators;
//    private List<String> subjects;
//    private List<String> bookshelves;
    private List<String> languages;
//    private boolean copyright;
//    private String mediaType;
//    private Map<String, String> formats;
//    private int downloadCount;
}
