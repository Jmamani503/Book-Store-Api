package com.booknest.application.port.input;

import com.booknest.domain.model.Transaction;
import java.util.List;
import java.util.UUID;

public interface TransactionServicePort {
    Transaction createTransaction(Transaction transaction);
    Transaction getTransaction(UUID id);
    List<Transaction> getAllTransaction();
    Transaction updateTransaction(UUID id, Transaction transaction);
    void  deleteTransaction(UUID id);
    List<Transaction> getAllTransactionByStock(UUID stockId);
}
