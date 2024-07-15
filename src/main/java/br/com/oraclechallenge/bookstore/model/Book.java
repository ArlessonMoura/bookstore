package br.com.oraclechallenge.bookstore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;

    @ManyToMany
    private List<Author> authors;

}
