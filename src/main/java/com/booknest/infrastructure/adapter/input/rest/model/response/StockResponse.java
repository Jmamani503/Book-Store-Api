package com.booknest.infrastructure.adapter.input.rest.model.response;

import com.booknest.domain.model.Book;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockResponse {
    private UUID id;
    private int quantity;
    private int minQuantity;
    private int maxQuantity;
    private LocalDateTime lastUpdated;
    private BookResponse book;
}
