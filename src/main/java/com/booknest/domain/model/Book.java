package com.booknest.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Book {

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
