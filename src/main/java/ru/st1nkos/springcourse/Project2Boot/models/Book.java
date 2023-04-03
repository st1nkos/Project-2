package ru.st1nkos.springcourse.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_name")
    @NotEmpty(message = "Название книги не должно быть пустым")
    private String bookName;

    @Column(name = "author")
    @NotEmpty(message = "Автор не должен быть пустым")
    private String author;

    @Column(name = "book_date")
    @Min(value = 1800, message = "Год должен быть больше, чем 1800")
    private int bookDate;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person owner;

    @Transient
    private boolean expired;




    public Book() {
    }

    public Book(String bookName, String author, int bookDate) {
        this.bookName = bookName;
        this.author = author;
        this.bookDate = bookDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookDate() {
        return bookDate;
    }

    public void setBookDate(int bookDate) {
        this.bookDate = bookDate;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
