package br.com.oraclechallenge.bookstore.service;

import br.com.oraclechallenge.bookstore.model.Book;
import br.com.oraclechallenge.bookstore.model.Person;
import br.com.oraclechallenge.bookstore.repository.AuthorRepository;
import br.com.oraclechallenge.bookstore.repository.BookRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public void saveBookAndAuthors(Book book) {
        bookRepository.save(book);
        authorRepository.save(book.getAuthors());
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Person> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Person> listAuthorsAliveOnDate(Year date) {
        return authorRepository.findAliveAuthors(date).orElseThrow(() -> new NoResultException("It was a bad time for Authors, we can't find anyone else alive"));
    }

    public List<Book> findBooksByLanguage(String language) {
        var listLanguague = new ArrayList<String>();
        listLanguague.add(language);
        return bookRepository.findByLanguages(listLanguague);
    }
}
