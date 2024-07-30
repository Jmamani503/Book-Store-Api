package com.booknest.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    STOCK_IN("stockin"),
    STOCK_OUT("stockout");
    private final String value;

    TransactionType(String value){
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }
    public static TransactionType fromString(String value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.value.equalsIgnoreCase(value.replace(" ", ""))) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown transaction type: " + value);
    }
}
