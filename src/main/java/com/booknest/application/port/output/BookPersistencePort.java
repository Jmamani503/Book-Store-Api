package com.booknest.application.port.output;

import com.booknest.domain.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookPersistencePort {

    Book save(Book book);
    Optional<Book> findById(UUID id);
    List<Book> findAll();
    void deleteById(UUID id);
}
