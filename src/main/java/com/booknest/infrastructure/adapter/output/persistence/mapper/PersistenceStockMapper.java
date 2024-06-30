package com.booknest.infrastructure.adapter.output.persistence.mapper;

import com.booknest.domain.model.Stock;
import com.booknest.infrastructure.adapter.output.persistence.entity.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class PersistenceStockMapper {

    private final PersistenceBookMapper bookMapper;
    public PersistenceStockMapper(PersistenceBookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public Stock toStock(StockEntity stockEntity){
        System.out.println(stockEntity.getBookEntity().getId());
        return Stock.builder()
                .id(stockEntity.getId())
                .quantity(stockEntity.getQuantity())
                .minQuantity(stockEntity.getMinQuantity())
                .maxQuantity(stockEntity.getMaxQuantity())
                .lastUpdated(stockEntity.getLastUpdated())
                .book(bookMapper.toBook(stockEntity.getBookEntity()))
                .build();
    }

    public StockEntity toStockEntity(Stock stock){
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stock.getId());
        stockEntity.setQuantity(stock.getQuantity());
        stockEntity.setMinQuantity(stock.getMinQuantity());
        stockEntity.setMaxQuantity(stock.getMaxQuantity());
        stockEntity.setLastUpdated(stock.getLastUpdated());
        stockEntity.setBookEntity(bookMapper.toBookEntity(stock.getBook()));
        return stockEntity;
    }
}
