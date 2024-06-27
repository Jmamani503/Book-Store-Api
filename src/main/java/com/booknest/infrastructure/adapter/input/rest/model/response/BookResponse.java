package com.booknest.infrastructure.adapter.input.rest.model.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {
    private UUID id;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private LocalDate publishedDate;
    private double price;
    private String image;
    private String description;
}
