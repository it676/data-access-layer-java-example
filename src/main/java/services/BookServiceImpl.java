package services;

import entitites.BookEntity;
import mappers.BookEntityMapper;
import mappers.BookMapper;
import models.Book;
import persistence.daos.BookDao;
import persistence.daos.BookDaoImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();
    private final BookMapper bookMapper = new BookEntityMapper();

    @Override
    public Book save(Book book) {
        BookEntity bookEntity = bookMapper.modelToEntity(book);
        BookEntity savedBookEntity = this.bookDao.save(bookEntity);
        return book;
    }

    @Override
    public Book findById(Integer id) {
        Optional<BookEntity> entityOptional = this.bookDao.findById(id);
        if (entityOptional.isEmpty()) {
            throw new RuntimeException("Book with id: " + id + " was not found!");
        }

        BookEntity bookEntity = entityOptional.get();
        return bookMapper.entityToModel(bookEntity);

    }

    @Override
    public List<Book> findAll() {
        List<BookEntity> bookEntityList = this.bookDao.findAll();
        return bookEntityList.stream().map(bookMapper::entityToModel).collect(Collectors.toList());

    }

    @Override
    public List<Book> findAllBy(Predicate<BookEntity> predicate) {

        return this.bookDao.findAllBy(predicate).stream()
                .map(bookMapper::entityToModel)
                .collect(Collectors.toList());
    }


    @Override
    public Book update(Book book, Integer id) {
        Optional<BookEntity> optionalBookEntity = this.bookDao.update(bookMapper.modelToEntity(book), id);
        if (optionalBookEntity.isEmpty()) {
            throw new RuntimeException("Book with id: " + id + " was not found!");
        }
        return bookMapper.entityToModel(optionalBookEntity.get());
    }

    @Override
    public String delete(Integer id) {
        this.bookDao.delete(id);
        return "Book has been deleted successfully!";
    }
}
