package com.booknest.infrastructure.adapter.output.persistence;

import com.booknest.application.port.output.StockPersistencePort;
import com.booknest.domain.model.Stock;
import com.booknest.domain.model.Transaction;
import com.booknest.infrastructure.adapter.output.persistence.entity.StockEntity;
import com.booknest.infrastructure.adapter.output.persistence.mapper.PersistenceStockMapper;
import com.booknest.infrastructure.adapter.output.persistence.mapper.PersistenceTransactionMapper;
import com.booknest.infrastructure.adapter.output.persistence.repository.StockRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StockPersistenceAdapter implements StockPersistencePort {

    private final StockRepository repository;
    private final PersistenceStockMapper mapper;

    public StockPersistenceAdapter(
        StockRepository repository,
        PersistenceStockMapper mapper
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Stock save(Stock stock) {
        return mapper.toStock(repository.save(mapper.toStockEntity(stock)));
    }

    @Override
    public Optional<Stock> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toStock);
    }

    @Override
    public List<Stock> findAll() {
//        return mapper.toStockList(repository.findAll());
        return repository.findAll().stream()
                .map(mapper::toStock)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Stock> findByBookEntityId(UUID bookId){
        return repository.findByBookEntityId(bookId)
                .map(mapper::toStock);
    }
}
