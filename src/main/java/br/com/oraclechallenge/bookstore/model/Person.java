package br.com.oraclechallenge.bookstore.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Person {

    private Integer birthYear;
    private Integer deathYear;
    private String name;

    public Integer getDeathYear() {
        return deathYear;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getName() {
        return name;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public void setName(String name) {
        this.name = name;
    }
}
