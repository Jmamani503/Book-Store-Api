package com.booknest.infrastructure.adapter.input.rest;

import com.booknest.domain.exception.*;
import com.booknest.domain.model.ErrorResponse;
import com.booknest.domain.util.ErrorCatalog;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse handleContentNotFound(){
        return ErrorResponse.builder()
                .code(ErrorCatalog.BOOK_NOT_FOUND.getCode())
                .message(ErrorCatalog.BOOK_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StockNotFoundException.class)
    public ErrorResponse handleStockNotFound(){
        return ErrorResponse.builder()
                .code(ErrorCatalog.STOCK_NOT_FOUND.getCode())
                .message(ErrorCatalog.STOCK_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(StockAlreadyExistsException.class)
    public ErrorResponse handleStockAlreadyExistsException(){
        return ErrorResponse.builder()
                .code(ErrorCatalog.STOCK_CONFLICT.getCode())
                .message(ErrorCatalog.STOCK_CONFLICT.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(ErrorCatalog.INVALID_CONTENT.getCode())
                .message(ErrorCatalog.INVALID_CONTENT.getMessage())
                .details(bindingResult.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StockExceededException.class)
    public ErrorResponse handleStockExceeded(){
        return ErrorResponse.builder()
                .code(ErrorCatalog.STOCK_EXCEEDED.getCode())
                .message(ErrorCatalog.STOCK_EXCEEDED.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughBooksException.class)
    public ErrorResponse handleNotEnoughStock(){
        return ErrorResponse.builder()
                .code(ErrorCatalog.NOT_ENOUGH_STOCK.getCode())
                .message(ErrorCatalog.NOT_ENOUGH_STOCK.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception){
        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
