package com.booknest.infrastructure.adapter.input.rest;

import com.booknest.application.port.input.StockServicePort;
import com.booknest.infrastructure.adapter.input.rest.mapper.RestStockMapper;
import com.booknest.infrastructure.adapter.input.rest.model.request.StockCreateRequest;
import com.booknest.infrastructure.adapter.input.rest.model.response.StockResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockServicePort service;
    private final RestStockMapper mapper;

    public StockController(StockServicePort service, RestStockMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<StockResponse> createStock(@Valid @RequestBody StockCreateRequest stock){
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(mapper.toStockResponse(
                                service.createStock(
                                        mapper.toStock(stock)
                                )
                        ));
    }
    @GetMapping("/{id}")
    public StockResponse getStock(@PathVariable UUID id){
        return mapper.toStockResponse(service.getStock(id));
    }
    @GetMapping
    public List<StockResponse> getAllStocks(){
        return service.getAllStocks().stream()
                .map(mapper::toStockResponse)
                .collect(Collectors.toList());
    }
    @PutMapping("/{id}")
    public StockResponse updateStock(@PathVariable UUID id, @Valid @RequestBody StockCreateRequest stock){
        return  mapper.toStockResponse(service.updateStock(id, mapper.toStock(stock)));
    }
    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable UUID id){
        service.deleteStock(id);
    }
}
