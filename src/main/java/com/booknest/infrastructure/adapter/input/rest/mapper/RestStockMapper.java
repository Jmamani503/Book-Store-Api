package com.booknest.infrastructure.adapter.input.rest.mapper;

import com.booknest.domain.model.Book;
import com.booknest.domain.model.Stock;
import com.booknest.infrastructure.adapter.input.rest.model.request.StockCreateRequest;
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
                .quantity(stockCreateRequest.getQuantity())
                .minQuantity(stockCreateRequest.getMinQuantity())
                .maxQuantity(stockCreateRequest.getMaxQuantity())
                .book(book)
                .build();
    }

    public StockResponse toStockResponse(Stock stock){

        return StockResponse.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .minQuantity(stock.getMinQuantity())
                .maxQuantity(stock.getMaxQuantity())
                .lastUpdated(stock.getLastUpdated())
                .book(bookMapper.toBookResponse(stock.getBook()))
                .build();
    }

}
