package com.booknest.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int quantity;
    private int minQuantity;
    private int maxQuantity;
    private LocalDateTime lastUpdated;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "book_id",
            referencedColumnName = "id"
    )
    private BookEntity bookEntity;
    @OneToMany(
            mappedBy = "stockEntity",
            fetch = FetchType.LAZY
    )
    private List<TransactionEntity> transactionEntities;

    public void addTransaction(TransactionEntity transactionEntity) {
        transactionEntities.add(transactionEntity);
        transactionEntity.setStockEntity(this);
    }
}
