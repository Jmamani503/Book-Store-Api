package com.booknest.infrastructure.adapter.input.rest;

import com.booknest.application.port.input.StockServicePort;
import com.booknest.application.port.input.TransactionServicePort;
import com.booknest.infrastructure.adapter.input.rest.mapper.RestTransactionMapper;
import com.booknest.infrastructure.adapter.input.rest.model.request.TransactionCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
public class TransactionCotroller {

    private final TransactionServicePort service;
    private final RestTransactionMapper mapper;
    private final StockServicePort stockService;
    private final RestTransactionMapper stockMapper;

    public TransactionCotroller(TransactionServicePort service, RestTransactionMapper mapper, StockServicePort stockService, RestTransactionMapper stockMapper) {
        this.service = service;
        this.mapper = mapper;
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionCreateRequest transaction){
        TransactionResponse transactionResponse = mapper.toTransactionResponse(service.createTransaction(mapper.toTransaction(transaction)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionResponse);
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransaction(@PathVariable UUID id){
        return mapper.toTransactionResponse(service.getTransaction(id));
    }

    @GetMapping
    public List<TransactionResponse> getAllTransactions(){
        return mapper.toTransactionResponseList(service.getAllTransaction());
    }

    @PutMapping("/{id}")
    public TransactionResponse updateTransaction(@PathVariable UUID id,@Valid @RequestBody TransactionCreateRequest transaction){
        return mapper.toTransactionResponse(service.updateTransaction(id, mapper.toTransaction(transaction)));
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable UUID id){
        service.deleteTransaction(id);
    }
    @GetMapping("/stock/{idStock}")
    public List<TransactionResponse> getAllTransactionByStock(@PathVariable UUID idStock){
        return mapper.toTransactionResponseList(service.getAllTransactionByStock(idStock));
    }
}
