package br.com.oraclechallenge.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    private List<String> subjects;

    @ManyToMany
    private List<Author> authors;

    @ElementCollection
    private List<Person> translators;

    @ElementCollection
    private List<String> bookshelves;

    @ManyToMany
    @JoinTable(
            name = "book_languages",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages;

    private Boolean copyright;

    private String mediaType;

    @ElementCollection
    @CollectionTable(name = "book_formats")
    @MapKeyColumn(name = "format_mime_type")
    @Column(name = "format_url")
    private Map<String, String> formats;

    private Integer downloadCount;


    public String getTitle() {
        return title;
    }


    public String getLanguage() {
        return languages != null ? languages.toString() : "";
    }

    public List<Author> getAuthors() {
        return authors;
    }

}
