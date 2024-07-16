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

    @Query(value = "select p.id, p.name, p.birth_date, p.death_date from person p where :year between p.birth_date and p.death_date", nativeQuery = true)
    Optional<List<Person>> findAliveAuthors(Year year);
}
