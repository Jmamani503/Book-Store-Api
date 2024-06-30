package com.booknest.infrastructure.adapter.input.rest.mapper;

import com.booknest.domain.model.Book;
import com.booknest.infrastructure.adapter.input.rest.model.request.BookCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.BookResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookRestMapper {
    Book toBook(BookCreateRequest book);
    BookResponse toBookResponse(Book book);
    List<BookResponse> toBookResponseList(List<Book> list);
 }
