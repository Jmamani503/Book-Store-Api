package com.booknest.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private LocalDate publishedDate;
    private double price;
    private String image;
    private String description;
    @OneToOne(
            mappedBy = "bookEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private StockEntity stockEntity;
}
