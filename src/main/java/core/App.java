package core;

import entitites.BookEntity;
import models.Book;
import services.BookService;
import services.BookServiceImpl;


public class App {
    public static void main(String[] args) {

        BookService bookService = new BookServiceImpl();

        bookService.update(new Book("Java Updated!", 900.56), 1);
    }
}
