package com.booknest.application.service;

import com.booknest.application.port.input.StockServicePort;
import com.booknest.application.port.output.BookPersistencePort;
import com.booknest.application.port.output.StockPersistencePort;
import com.booknest.domain.exception.BookNotFoundException;
import com.booknest.domain.exception.StockAlreadyExistsException;
import com.booknest.domain.exception.StockNotFoundException;
import com.booknest.domain.model.Book;
import com.booknest.domain.model.Stock;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StockService implements StockServicePort {
    private final StockPersistencePort persistence;
    private final BookPersistencePort bookPersistence;
    public StockService(
        StockPersistencePort persistence,
        BookPersistencePort bookPersistence
    ) {
        this.persistence = persistence;
        this.bookPersistence = bookPersistence;
    }

    @Override
    public Stock createStock(Stock stock) {
        Book book = bookPersistence.findById(stock.getBook().getId())
                .orElseThrow(BookNotFoundException::new);
        stock.setBook(book);
        stock.setLastModified(LocalDateTime.now());
        return persistence.save(stock);
    }

    @Override
    public Stock getStock(UUID id) {
        return persistence.findById(id)
                .orElseThrow(StockNotFoundException::new);
    }

    @Override
    public List<Stock> getAllStocks() {
        return persistence.findAll();
    }

    @Override
    public Stock updateStock(UUID id, Stock stock) {
        return persistence.findById(id)
                .map(updatedStock -> {
                    updatedStock.setMaxQuantity(stock.getMaxQuantity());
                    updatedStock.setMinQuantity(stock.getMinQuantity());
                    updatedStock.setLastModified(LocalDateTime.now());
                    updatedStock.setMarkup(stock.getMarkup());
                    updatedStock.setDescription(stock.getDescription());
                    return persistence.save(updatedStock);
                })
                .orElseThrow(StockNotFoundException::new);
    }

    @Override
    public void deleteStock(UUID id) {
        if (persistence.findById(id).isEmpty()){
            throw new StockNotFoundException();
        }
        persistence.deleteById(id);
    }
}
