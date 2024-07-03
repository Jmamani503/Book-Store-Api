package com.booknest.domain.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    BOOK_NOT_FOUND("ERR_BOOK_001","Book not found."),
    NOT_ENOUGH_STOCK("ERR_BOOK_002","Not enough stock"),
    STOCK_NOT_FOUND("ERR_STOCK_001","Stock not found"),
    STOCK_EXCEEDED("ERR_STOCK_002","Stock capacity exceeded"),
    INVALID_CONTENT("ERR_CONTENT_002","Invalid content parameters."),
    GENERIC_ERROR("ERR_GEN_001","An unexpected error occurred."),
    STOCK_CONFLICT("ERR_STOCK_004","A Stock with that Book already exists");
    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
