package br.com.oraclechallenge.bookstore.repository;

import br.com.oraclechallenge.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameContaining(String name);
    List<Author> findByBirthDate(LocalDate birthDate);
}