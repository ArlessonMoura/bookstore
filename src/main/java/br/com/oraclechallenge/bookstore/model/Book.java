package br.com.oraclechallenge.bookstore.model;

import br.com.oraclechallenge.bookstore.dto.GutendexDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    public Book() {
    }

    public Book(GutendexDTO gutendexDTO) {
        this.title = gutendexDTO.title();
        Person author = new Person(gutendexDTO.authors().get(0));
        this.author = author;
        this.languages = gutendexDTO.languages();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    private List<String> subjects;

    private Person author;

    @ElementCollection
    private List<Person> translators;

    @ElementCollection
    private List<String> bookshelves;

    private List<String> languages;

    private Boolean copyright;

    private String mediaType;

    private Map<String, String> formats;

    private Integer downloadCount;

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return languages != null ? languages.toString() : "";
    }

    public Person getAuthors() {
        return author;
    }

}
