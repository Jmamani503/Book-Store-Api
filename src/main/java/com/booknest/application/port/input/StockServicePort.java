package com.booknest.application.port.input;

import com.booknest.domain.model.Stock;

import java.util.List;
import java.util.UUID;

public interface StockServicePort {

    Stock createStock(Stock stock);
    Stock getStock(UUID id);
    List<Stock> getAllStocks();
    Stock updateStock(UUID id, Stock stock);
    void deleteStock(UUID id);
}

