package com.booknest.infrastructure.adapter.output.persistence;

import com.booknest.application.port.output.TransactionPersistencePort;
import com.booknest.domain.model.Transaction;
import com.booknest.infrastructure.adapter.output.persistence.mapper.PersistenceTransactionMapper;
import com.booknest.infrastructure.adapter.output.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TransactionPersistenceAdapter implements TransactionPersistencePort {

    private final TransactionRepository repository;
    private final PersistenceTransactionMapper mapper;

    public TransactionPersistenceAdapter(
        TransactionRepository repository,
        PersistenceTransactionMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return mapper.toTransaction(repository.save(mapper.toTransactionEntity(transaction)));
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toTransaction);
    }

    @Override
    public List<Transaction> findAll() {
        return mapper.toTransactionList(repository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Transaction> findAllTransactionsByStock(UUID id) {
        return mapper.toTransactionList(repository.findByStockEntityId(id));
    }
}
