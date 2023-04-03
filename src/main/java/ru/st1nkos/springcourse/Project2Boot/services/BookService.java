package ru.st1nkos.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.st1nkos.springcourse.Project2Boot.models.Book;
import ru.st1nkos.springcourse.Project2Boot.models.Person;
import ru.st1nkos.springcourse.Project2Boot.repositories.BookRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findAll(boolean sortByYear){
        if (sortByYear){
            return bookRepository.findAll(Sort.by("bookDate"));
        }else {
            return bookRepository.findAll();
        }

    }

    public List<Book> findAllWithPagination(Integer page, Integer booksPerPage,boolean sortByYear){
        if (sortByYear){
            return bookRepository.findAll(PageRequest.of(page,booksPerPage, Sort.by("bookDate"))).getContent();
        }else {
            return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
        }
    }

    public Book findOne(int id){
        Optional<Book> findBook = bookRepository.findById(id);
        return findBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id,Book updatedBook){
        updatedBook.setId(id);
        updatedBook.setOwner(updatedBook.getOwner());
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakenAt(null);
                });
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                    book.setTakenAt(new Date());
                }
        );
    }

    public List<Book> searchByBookName(String query){
       return bookRepository.findByBookNameStartingWith(query);
    }

}
