package com.booknest.infrastructure.adapter.output.persistence;

import com.booknest.application.port.output.BookPersistencePort;
import com.booknest.domain.model.Book;
import com.booknest.infrastructure.adapter.output.persistence.mapper.BookPersistenceMapper;
import com.booknest.infrastructure.adapter.output.persistence.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
public class BookPersistanceAdapter implements BookPersistencePort {

    private final BookRepository repository;
    private final BookPersistenceMapper mapper;

    public BookPersistanceAdapter(BookRepository repository, BookPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Book save(Book book) {
        return mapper.toBook(repository.save(mapper.toBookEntity(book)));
    }

    @Override
    public Optional<Book> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toBook);
    }

    @Override
    public List<Book> findAll() {
        return mapper.toBookList(repository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
