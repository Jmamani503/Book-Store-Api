package com.booknest.infrastructure.adapter.output.persistence.entity;

import com.booknest.domain.model.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String type;
    private int quantity;
    private LocalDateTime transactionDate;
    private String note;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private StockEntity stockEntity;



}
