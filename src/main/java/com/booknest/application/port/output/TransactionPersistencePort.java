package com.booknest.application.port.output;

import com.booknest.domain.model.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionPersistencePort {

    Transaction save(Transaction transaction);
    Optional<Transaction> findById(UUID id);
    List<Transaction> findAll();
    void  deleteById(UUID id);
    List<Transaction> findAllTransactionsByStock(UUID id);
}
