package br.com.oraclechallenge.bookstore.repository;

import br.com.oraclechallenge.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguage(String language);
}
