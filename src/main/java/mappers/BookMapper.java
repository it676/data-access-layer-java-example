package mappers;

import entitites.BookEntity;
import models.Book;

public interface BookMapper {
    Book entityToModel(BookEntity entity);

    BookEntity modelToEntity(Book book);
}
