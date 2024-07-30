package com.booknest.infrastructure.adapter.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockCreateRequest {
    @NotNull(message = "Field minQuantity cannot be null")
    private int minQuantity;
    @NotNull(message = "Field maxQuantity cannot be null")
    private int maxQuantity;
    @NotNull(message = "Field markup cannot be null")
    private  double markup;
    @NotNull(message = "Field idBook cannot be blank")
    private UUID idBook;
    @NotBlank(message = "Field description cannot be blank")
    private String description;
}
