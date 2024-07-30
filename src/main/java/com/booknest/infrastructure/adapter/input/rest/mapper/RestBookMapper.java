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
                .genres(bookResponse.getGenresEnumSet())
                .image(bookResponse.getImage())
                .build();
    }
    public BookResponse toBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genres(book.getStringGenres())
                .image(book.getImage())
                .build();
    }
}
