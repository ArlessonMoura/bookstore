package br.com.oraclechallenge.bookstore.service;

import br.com.oraclechallenge.bookstore.model.Author;
import br.com.oraclechallenge.bookstore.model.Book;
import br.com.oraclechallenge.bookstore.repository.AuthorRepository;
import br.com.oraclechallenge.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public void saveBookAndAuthors(Book book) {
        bookRepository.save(book);
        book.getAuthors().forEach(author -> authorRepository.save(author));
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsAliveOnDate(LocalDate date) {
        return authorRepository.findAll().stream()
                .filter(author -> (author.getBirthDate() != null && !author.getBirthDate().isAfter(date))
                        && (author.getDeathDate() == null || !author.getDeathDate().isBefore(date)))
                .toList();
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getLanguage().equalsIgnoreCase(language))
                .toList();
    }
}
