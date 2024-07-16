package br.com.oraclechallenge.bookstore.repository;

import br.com.oraclechallenge.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.languages l WHERE l.name = :language")
    List<Book> findByLanguage(String language);
}
