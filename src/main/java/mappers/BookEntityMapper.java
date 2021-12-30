package mappers;

import entitites.BookEntity;
import models.Book;

public class BookEntityMapper implements BookMapper {
    @Override
    public Book entityToModel(BookEntity entity) {
        Book book = new Book();
        book.setTitle(entity.getTitle());
        book.setPrice(entity.getPrice());
        return book;
    }

    @Override
    public BookEntity modelToEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPrice(book.getPrice());
        return bookEntity;
    }
}
