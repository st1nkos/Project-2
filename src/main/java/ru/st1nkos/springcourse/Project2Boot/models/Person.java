package ru.st1nkos.springcourse.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.List;


@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Введите полное имя")
    @Size(min=10,max=150, message = "Введите полное имя")
    @Column(name = "full_name")
    private String fullName;
    @Min(value = 1900,message = "Год рождения должен быть больше 1900")
    @Column(name = "born_year")
    private int bornYear;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;


    public Person() {
    }

    public Person(String fullName, int bornYear) {
        this.fullName = fullName;
        this.bornYear = bornYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", bornYear=" + bornYear +
                '}';
    }
}



