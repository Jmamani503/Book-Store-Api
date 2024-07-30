package com.booknest.domain.model;

import com.booknest.domain.enums.TransactionType;
import com.booknest.domain.exception.NotEnoughBooksException;
import com.booknest.domain.exception.StockExceededException;
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
    private double purchasePrice;
    private double salePrice;
    private double markup;
    private int totalSales;
    private int totalPurchases;
    private double totalSpending;
    private double totalEarnings;
    private String description;
    private LocalDateTime lastModified;
    private Book book;

    public boolean canPurchase() {
        return quantity <= maxQuantity;
    }
    public boolean canSell(int quantityChange) {
        return quantity - quantityChange >= 0;
    }
    public void updateStockQuantity(Transaction transaction){
        switch (transaction.getType()) {
            case STOCK_IN -> {
                if (!canPurchase()) throw new StockExceededException();
                this.quantity = this.getQuantity() + transaction.getQuantity();
                double newSalePrice =
                        (this.totalSpending + (transaction.getPrice() * transaction.getQuantity())) /
                                (this.totalPurchases + transaction.getQuantity());
                this.setPurchasePrice(newSalePrice);
                this.setSalePrice(newSalePrice * this.markup);
                this.totalPurchases += transaction.getQuantity();
                this.totalSpending += transaction.getQuantity() * transaction.getPrice();
            }
            case STOCK_OUT -> {
                if (!canSell(transaction.getQuantity())) throw new NotEnoughBooksException();
                this.quantity = this.getQuantity() - transaction.getQuantity();
                this.totalSales += transaction.getQuantity();
                this.totalEarnings += transaction.getQuantity() * transaction.getPrice();
            }
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }
        this.lastModified = LocalDateTime.now();
    }

}
