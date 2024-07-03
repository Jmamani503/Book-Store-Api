package com.booknest.infrastructure.adapter.input.rest.model.request;

import com.booknest.domain.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockCreateRequest {

    @NotNull(message = "Field quantity connnot be null or empty")
    private int quantity;
    @NotNull(message = "Field minQuantity connnot be null or empty")
    private int minQuantity;
    @NotNull(message = "Field maxQuantity connnot be null or empty")
    private int maxQuantity;
    @NotNull(message = "Field idBook connnot be null or empty")
    private UUID idBook;
}
