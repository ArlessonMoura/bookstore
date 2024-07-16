package br.com.oraclechallenge.bookstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Year;
import java.time.Year;

@Entity
public class Person {

    public Person() {
    }

    public Person(Person person) {
        this.name = person.name;
        this.deathDate = person.deathDate;
        this.birthDate = person.birthDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonProperty("birth_year")
    private Year birthDate;

    @JsonProperty("death_year")
    private Year deathDate;

    public String getAuthor() {
        return "Author: " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Year birthDate) {
        this.birthDate = birthDate;
    }

    public Year getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Year deathDate) {
        this.deathDate = deathDate;
    }
}
