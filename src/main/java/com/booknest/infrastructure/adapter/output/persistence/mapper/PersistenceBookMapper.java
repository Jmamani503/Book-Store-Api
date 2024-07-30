package com.booknest.infrastructure.adapter.output.persistence.mapper;

import com.booknest.domain.model.Book;
import com.booknest.infrastructure.adapter.output.persistence.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class PersistenceBookMapper {

    public Book toBook(BookEntity bookEntity){
        return Book.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .genres(bookEntity.getGenreEnumSet())
                .image(bookEntity.getImage())
                .build();
    }
    public BookEntity toBookEntity(Book book){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setGenres(book.getStringGenres());
        bookEntity.setImage(book.getImage());
        return bookEntity;
    }
}
