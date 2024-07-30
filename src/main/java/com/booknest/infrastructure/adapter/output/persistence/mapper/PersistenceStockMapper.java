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
        return Stock.builder()
                .id(stockEntity.getId())
                .quantity(stockEntity.getQuantity())
                .minQuantity(stockEntity.getMinQuantity())
                .maxQuantity(stockEntity.getMaxQuantity())
                .salePrice(stockEntity.getSalePrice())
                .purchasePrice(stockEntity.getPurchasePrice())
                .markup(stockEntity.getMarkup())
                .totalSales(stockEntity.getTotalSales())
                .totalPurchases(stockEntity.getTotalPurchases())
                .totalSpending(stockEntity.getTotalSpending())
                .totalEarnings(stockEntity.getTotalEarnings())
                .description(stockEntity.getDescription())
                .lastModified(stockEntity.getLastUpdated())
                .book(bookMapper.toBook(stockEntity.getBookEntity()))
                .build();
    }

    public StockEntity toStockEntity(Stock stock){
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stock.getId());
        stockEntity.setQuantity(stock.getQuantity());
        stockEntity.setMinQuantity(stock.getMinQuantity());
        stockEntity.setMaxQuantity(stock.getMaxQuantity());
        stockEntity.setPurchasePrice(stock.getPurchasePrice());
        stockEntity.setSalePrice(stock.getSalePrice());
        stockEntity.setMarkup(stock.getMarkup());
        stockEntity.setTotalSales(stock.getTotalSales());
        stockEntity.setTotalPurchases(stock.getTotalPurchases());
        stockEntity.setTotalEarnings(stock.getTotalEarnings());
        stockEntity.setTotalSpending(stock.getTotalSpending());
        stockEntity.setDescription(stock.getDescription());
        stockEntity.setLastUpdated(stock.getLastModified());
        stockEntity.setBookEntity(bookMapper.toBookEntity(stock.getBook()));
        return stockEntity;
    }
}
