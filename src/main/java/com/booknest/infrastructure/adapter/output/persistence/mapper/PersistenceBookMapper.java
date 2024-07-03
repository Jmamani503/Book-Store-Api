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
                .genre(bookEntity.getGenre())
                .publisher(bookEntity.getPublisher())
                .publishedDate(bookEntity.getPublishedDate())
                .price(bookEntity.getPrice())
                .image(bookEntity.getImage())
                .description(bookEntity.getDescription())
                .build();
    }
    public BookEntity toBookEntity(Book book){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setGenre(book.getGenre());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setPublishedDate(book.getPublishedDate());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setImage(book.getImage());
        bookEntity.setDescription(book.getDescription());
        return bookEntity;
    }
}
