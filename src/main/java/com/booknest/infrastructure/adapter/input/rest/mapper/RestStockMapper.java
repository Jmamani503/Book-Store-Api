package com.booknest.infrastructure.adapter.input.rest.mapper;

import com.booknest.domain.model.Book;
import com.booknest.domain.model.Stock;
import com.booknest.infrastructure.adapter.input.rest.model.request.StockCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.StockDetailsResponse;
import com.booknest.infrastructure.adapter.input.rest.model.response.StockResponse;
import org.springframework.stereotype.Component;

@Component
public class RestStockMapper {
    private final RestBookMapper bookMapper;
    public RestStockMapper(RestBookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public Stock toStock(StockCreateRequest stockCreateRequest){
        Book book = new Book();
        book.setId(stockCreateRequest.getIdBook());
        return Stock.builder()
                .minQuantity(stockCreateRequest.getMinQuantity())
                .maxQuantity(stockCreateRequest.getMaxQuantity())
                .markup(stockCreateRequest.getMarkup())
                .description(stockCreateRequest.getDescription())
                .book(book)
                .build();
    }

    public StockResponse toStockResponse(Stock stock){
        return StockResponse.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .lastUpdated(stock.getLastModified())
                .salePrice(stock.getSalePrice())
                .description(stock.getDescription())
                .book(bookMapper.toBookResponse(stock.getBook()))
                .build();
    }
    public StockDetailsResponse toStockDetailsResponse(Stock stock){
        return StockDetailsResponse.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .minQuantity(stock.getMinQuantity())
                .maxQuantity(stock.getMaxQuantity())
                .purchasePrice(stock.getPurchasePrice())
                .salePrice(stock.getSalePrice())
                .markup(stock.getMarkup())
                .totalSales(stock.getTotalSales())
                .totalPurchases(stock.getTotalPurchases())
                .totalSpending(stock.getTotalSpending())
                .totalEarnings(stock.getTotalEarnings())
                .description(stock.getDescription())
                .lastModified(stock.getLastModified())
                .book(bookMapper.toBookResponse(stock.getBook()))
                .build();
    }
}
