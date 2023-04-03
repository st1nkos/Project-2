package ru.st1nkos.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.st1nkos.springcourse.Project2Boot.models.Book;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByBookNameStartingWith(String bookName);
}
