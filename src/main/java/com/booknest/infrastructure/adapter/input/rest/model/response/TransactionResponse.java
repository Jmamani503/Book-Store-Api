package com.booknest.infrastructure.adapter.input.rest.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {

    private UUID id;
    private String type;
    private int quantity;
    private double price;
    private LocalDateTime transactionDate;
    private String note;
    private int beforeTransaction;
    private StockResponse stockResponse;
}
