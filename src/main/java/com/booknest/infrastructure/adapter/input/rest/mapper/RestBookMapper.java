package com.booknest.infrastructure.adapter.input.rest.mapper;

import com.booknest.domain.model.Book;
import com.booknest.infrastructure.adapter.input.rest.model.request.BookCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class RestBookMapper {
    public Book toBook(BookCreateRequest bookResponse){
        return Book.builder()
                .title(bookResponse.getTitle())
                .author(bookResponse.getAuthor())
                .genre(bookResponse.getGenre())
                .publisher(bookResponse.getPublisher())
                .publishedDate(bookResponse.getPublishedDate())
                .price(bookResponse.getPrice())
                .image(bookResponse.getImage())
                .description(bookResponse.getDescription())
                .build();
    }
    public BookResponse toBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .price(book.getPrice())
                .image(book.getImage())
                .description(book.getDescription())
                .build();
    }
}
