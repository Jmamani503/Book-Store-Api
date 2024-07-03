package com.booknest.domain.model;

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
    private LocalDateTime transactionDate;
    private String note;
    private Stock stock;
}
