package com.booknest.infrastructure.adapter.input.rest.mapper;

import com.booknest.domain.model.Stock;
import com.booknest.domain.model.Transaction;
import com.booknest.domain.model.TransactionType;
import com.booknest.infrastructure.adapter.input.rest.model.request.TransactionCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.TransactionResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestTransactionMapper {
    private final RestStockMapper stockMapper;
    public RestTransactionMapper(RestStockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    public TransactionResponse toTransactionResponse(Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .type(transaction.getType().toString())
                .quantity(transaction.getQuantity())
                .transactionDate(transaction.getTransactionDate())
                .note(transaction.getNote())
                .stockResponse(stockMapper.toStockResponse(transaction.getStock()))
                .build();
    }

    public Transaction toTransaction(TransactionCreateRequest transactionCreateRequest){
        Stock stock = new Stock();
        stock.setId(transactionCreateRequest.getIdStock());
        return Transaction.builder()
                .type(TransactionType.fromString(transactionCreateRequest.getType()))
                .quantity(transactionCreateRequest.getQuantity())
                .note(transactionCreateRequest.getNote())
                .stock(stock)
                .build();
    }

    public List<TransactionResponse> toTransactionResponseList(List<Transaction> transactionList){
        return transactionList.stream()
                .map(this::toTransactionResponse)
                .collect(Collectors.toList());
    }
}
