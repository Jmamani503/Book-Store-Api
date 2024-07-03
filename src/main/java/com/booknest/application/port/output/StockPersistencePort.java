package com.booknest.application.port.output;

import com.booknest.domain.model.Stock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockPersistencePort {

    Stock save(Stock stock);
    Optional<Stock> findById(UUID id);
    List<Stock> findAll();
    void deleteById(UUID id);
    Optional<Stock> findByBookEntityId(UUID bookId);
}
