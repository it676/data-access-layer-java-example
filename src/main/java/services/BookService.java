package services;

import entitites.BookEntity;
import models.Book;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface BookService {
    Book save(Book book);

    Book findById(Integer id);

    List<Book> findAll();

    List<Book> findAllBy(Predicate<BookEntity> predicate);


    Book update(Book entity, Integer id);

    String delete(Integer id);

}
