package com.booknest.application.service;

import com.booknest.application.port.input.BookServicePort;
import com.booknest.application.port.output.BookPersistencePort;
import com.booknest.domain.exception.BookNotFoundException;
import com.booknest.domain.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService implements BookServicePort {
    private final BookPersistencePort persistence;
    public BookService(BookPersistencePort persistence) {
        this.persistence = persistence;
    }

    @Override
    public Book createBook(Book book) {
        return persistence.save(book);
    }

    @Override
    public Book getBook(UUID id) {
        return persistence.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> getAllBooks() {
        return persistence.findAll();
    }

    @Override
    public Book updateBook(UUID id, Book book) {
        return persistence.findById(id)
                .map(updatedBook -> {
                    updatedBook.setTitle(book.getTitle());
                    updatedBook.setAuthor(book.getAuthor());
                    updatedBook.setGenre(book.getGenre());
                    updatedBook.setPublisher(book.getPublisher());
                    updatedBook.setPublishedDate(book.getPublishedDate());
                    updatedBook.setPrice(book.getPrice());
                    updatedBook.setImage(book.getImage());
                    updatedBook.setDescription(book.getDescription());
                    return persistence.save(updatedBook);
                })
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public void deleteBook(UUID id) {
        if (persistence.findById(id).isEmpty()){
            throw new BookNotFoundException();
        }
        persistence.deleteById(id);
    }
}
