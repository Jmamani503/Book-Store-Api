package com.booknest.infrastructure.adapter.output.persistence.mapper;

import com.booknest.domain.model.Transaction;
import com.booknest.domain.model.TransactionType;
import com.booknest.infrastructure.adapter.output.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersistenceTransactionMapper {
    private final PersistenceStockMapper stockMapper;
    public PersistenceTransactionMapper(PersistenceStockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }
    public Transaction toTransaction(TransactionEntity transactionEntity){
        return Transaction.builder()
                .id(transactionEntity.getId())
                .type(TransactionType.fromString(transactionEntity.getType()))
                .quantity(transactionEntity.getQuantity())
                .transactionDate(transactionEntity.getTransactionDate())
                .note(transactionEntity.getNote())
                .stock(stockMapper.toStock(transactionEntity.getStockEntity()))
                .build();
    }

    public TransactionEntity toTransactionEntity(Transaction transaction){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setType(transaction.getType().toString());
        transactionEntity.setQuantity(transaction.getQuantity());
        transactionEntity.setTransactionDate(transaction.getTransactionDate());
        transactionEntity.setNote(transaction.getNote());
        transactionEntity.setStockEntity(stockMapper.toStockEntity(transaction.getStock()));
        return transactionEntity;
    }

    public List<Transaction> toTransactionList(List<TransactionEntity> transactionEntities){
        return transactionEntities.stream()
                .map(this::toTransaction)
                .collect(Collectors.toList());
    }
}
