package com.booknest.domain.model;

import com.booknest.domain.enums.TransactionType;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Transaction {
    private UUID id;
    private TransactionType type;
    private int quantity;
    private double price;
    private LocalDateTime transactionDate;
    private String note;
    private int beforeTransaction;
    private Stock stock;
}
