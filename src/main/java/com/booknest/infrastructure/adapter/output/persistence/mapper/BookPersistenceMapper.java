package com.booknest.infrastructure.adapter.output.persistence.mapper;

import com.booknest.domain.model.Book;
import com.booknest.infrastructure.adapter.output.persistence.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookPersistenceMapper {
    Book toBook(BookEntity bookEntity);
    BookEntity toBookEntity(Book book);
    List<Book> toBookList(List<BookEntity> list);

}
