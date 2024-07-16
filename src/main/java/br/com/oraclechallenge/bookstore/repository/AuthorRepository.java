package br.com.oraclechallenge.bookstore.repository;

import br.com.oraclechallenge.bookstore.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Person, Long> {
    List<Person> findByNameContaining(String name);
    @Query(value = "select p from person p where p.birthDate >= :year and p.deathDate <= :year", nativeQuery = true)
    Optional<List<Person>> findAliveAuthors(Year year);
}