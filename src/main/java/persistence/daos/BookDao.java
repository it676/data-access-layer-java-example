package persistence.daos;

import entitites.BookEntity;
import persistence.CrudOperations;

import java.util.List;
import java.util.function.Predicate;

public interface BookDao extends CrudOperations<BookEntity, Integer> {

    List<BookEntity> findAllBy(Predicate<BookEntity> predicate);
}
