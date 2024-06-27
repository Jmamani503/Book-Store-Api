package com.booknest.infrastructure.adapter.input.rest;

import com.booknest.application.port.input.BookServicePort;
import com.booknest.infrastructure.adapter.input.rest.mapper.BookRestMapper;
import com.booknest.infrastructure.adapter.input.rest.model.request.BookCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.BookResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookServicePort service;
    private final BookRestMapper mapper;
    public BookController(BookServicePort service, BookRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest book){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toBookResponse(service.createBook(mapper.toBook(book))));
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable UUID id){
        return mapper.toBookResponse(service.getBook(id));
    }

    @GetMapping
    public List<BookResponse> getAllBook(){
        return mapper.toBookResponseList(service.getAllBooks());
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable UUID id, @Valid @RequestBody BookCreateRequest book){
        return mapper.toBookResponse(service.updateBook(id, mapper.toBook(book)));
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id){
        service.deleteBook(id);
    }
}
