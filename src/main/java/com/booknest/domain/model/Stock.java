package com.booknest.domain.model;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Stock {
    private UUID id;
    private int quantity;
    private int minQuantity;
    private int maxQuantity;
    private LocalDateTime lastUpdated;
    private Book book;

    public boolean canPurchase() {
        return quantity <= maxQuantity;
    }
    public boolean canSell(int quantityChange) {
        return quantity - quantityChange >= 0;
    }
}
