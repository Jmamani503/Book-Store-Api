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
public class TransactionCreateRequest {

    @NotBlank(message = "Field type cannot be blank")
    private String type;
    @NotNull(message = "Field quantity cannot be null")
    private int quantity;
    private String note;
    @NotNull(message = "Field idStock cannot be null or empty")
    private UUID idStock;
}
