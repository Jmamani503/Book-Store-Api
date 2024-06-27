package com.booknest.domain.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    CONTENT_NOT_FOUND("ERR_CONTENT_001","Content not found."),
    INVALID_CONTENT("ERR_CONTENT_002","Invalid content parameters."),
    GENERIC_ERROR("ERR_GEN_001","An unexpected error occurred.");
    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
