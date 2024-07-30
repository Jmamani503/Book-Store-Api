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
public class StockDetailsResponse {
    private UUID id;
    private int quantity;
    private int minQuantity;
    private int maxQuantity;
    private double purchasePrice;
    private double salePrice;
    private double markup;
    private int totalSales;
    private int totalPurchases;
    private double totalSpending;
    private double totalEarnings;
    private String description;
    private LocalDateTime lastModified;
    private BookResponse book;
}
