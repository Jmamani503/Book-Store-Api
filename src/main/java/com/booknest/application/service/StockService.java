package com.booknest.application.service;

import com.booknest.application.port.input.BookServicePort;
import com.booknest.application.port.input.StockServicePort;
import com.booknest.application.port.output.BookPersistencePort;
import com.booknest.application.port.output.StockPersistencePort;
import com.booknest.domain.exception.BookNotFoundException;
import com.booknest.domain.exception.StockAlreadyExistsException;
import com.booknest.domain.exception.StockNotFoundException;
import com.booknest.domain.model.Stock;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StockService implements StockServicePort {

    private final StockPersistencePort persitence;
    private final BookPersistencePort bookpersistence;
    public StockService(StockPersistencePort persitence, BookPersistencePort bookpersistence) {
        this.persitence = persitence;
        this.bookpersistence = bookpersistence;
    }

    @Override
    public Stock createStock(Stock stock) {
        if(persitence.findByBookEntityId(stock.getBook().getId()).isPresent()){
            throw  new StockAlreadyExistsException();
        }
        stock.setBook(bookpersistence.findById(
                stock.getBook().getId())
                .orElseThrow(BookNotFoundException::new)
        ) ;
        stock.setLastUpdated(LocalDateTime.now());
        return persitence.save(stock);
    }

    @Override
    public Stock getStock(UUID id) {
        return persitence.findById(id)
                .orElseThrow(StockNotFoundException::new);
    }

    @Override
    public List<Stock> getAllStocks() {
        return persitence.findAll();
    }

    @Override
    public Stock updateStock(UUID id, Stock stock) {
        return persitence.findById(id)
                .map(updatedStock -> {
                    updatedStock.setBook(stock.getBook());
                    updatedStock.setQuantity(stock.getQuantity());
                    updatedStock.setMaxQuantity(stock.getMaxQuantity());
                    updatedStock.setMinQuantity(stock.getMinQuantity());
                    return persitence.save(updatedStock);
                })
                .orElseThrow(StockNotFoundException::new);
    }

    @Override
    public void deleteStock(UUID id) {
        persitence.deleteById(id);
    }
}
