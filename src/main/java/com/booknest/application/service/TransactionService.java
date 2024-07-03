package com.booknest.application.service;

import com.booknest.application.port.input.TransactionServicePort;
import com.booknest.application.port.output.BookPersistencePort;
import com.booknest.application.port.output.StockPersistencePort;
import com.booknest.application.port.output.TransactionPersistencePort;
import com.booknest.domain.exception.*;
import com.booknest.domain.model.Stock;
import com.booknest.domain.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService implements TransactionServicePort {
    private final TransactionPersistencePort persistence;
    private final StockPersistencePort stockPersistence;
    public TransactionService(
        TransactionPersistencePort persistence,
        StockPersistencePort stockPersistence
    ) {
        this.persistence = persistence;
        this.stockPersistence = stockPersistence;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Stock stock = stockPersistence.findById(transaction.getStock().getId())
                .orElseThrow(StockNotFoundException::new);
        switch (transaction.getType()) {
            case STOCK_IN -> {
                if (!stock.canPurchase()) throw new StockExceededException();
                stock.setQuantity(stock.getQuantity() + transaction.getQuantity());
            }
            case STOCK_OUT -> {
                if (!stock.canSell(transaction.getQuantity())) throw new NotEnoughBooksException();
                stock.setQuantity(stock.getQuantity() - transaction.getQuantity());
            }
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }
        stock.setLastUpdated(LocalDateTime.now());
        stockPersistence.save(stock);
        transaction.setStock(stock);
        transaction.setTransactionDate(LocalDateTime.now());
        return persistence.save(transaction);
    }

    @Override
    public Transaction getTransaction(UUID id) {
        return persistence.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return persistence.findAll();
    }

    @Override
    public Transaction updateTransaction(UUID id, Transaction transaction) {
        return persistence.findById(id)
                .map(updatedTransaction -> {
                    updatedTransaction.setType(transaction.getType());
                    updatedTransaction.setQuantity(transaction.getQuantity());
                    updatedTransaction.setNote(transaction.getNote());
                    updatedTransaction.setTransactionDate(transaction.getTransactionDate());
                    return persistence.save(updatedTransaction);
                })
                .orElseThrow(TransactionNotFoundException::new);
    }

    @Override
    public void deleteTransaction(UUID id) {
        persistence.deleteById(id);
    }

    @Override
    public List<Transaction> getAllTransactionByStock(UUID stockId) {
        return persistence.findAllTransactionsByStock(stockId);
    }
}
