package com.booknest.application.port.input;

import com.booknest.domain.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookServicePort {
    Book createBook(Book book);
    Book getBook(UUID id);
    List<Book> getAllBooks();
    Book updateBook(UUID id, Book book);
    void deleteBook(UUID id);
}
