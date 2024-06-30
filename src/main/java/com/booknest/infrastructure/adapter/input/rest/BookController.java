package com.booknest.infrastructure.adapter.input.rest;

import com.booknest.application.port.input.BookServicePort;
import com.booknest.infrastructure.adapter.input.rest.mapper.RestBookMapper;
import com.booknest.infrastructure.adapter.input.rest.model.request.BookCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.BookResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookServicePort service;
    private final RestBookMapper mapper;
  
    public BookController(BookServicePort service, RestBookMapper mapper) {

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
        return service.getAllBooks().stream()
                .map(mapper::toBookResponse)
                .collect(Collectors.toList());
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
