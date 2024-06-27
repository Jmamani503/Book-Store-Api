package com.booknest.infrastructure.adapter.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookCreateRequest {

    @NotBlank(message = "Field title cannot be blank or empty")
    private String title;
    @NotBlank(message = "Field author cannot be blank or empty")
    private String author;
    @NotBlank(message = "Field genre cannot be blank or empty")
    private String genre;
    @NotBlank(message = "Field publisher cannot be blank or empty")
    private String publisher;
    @NotNull(message = "Field title publishedDate be blank or empty")
    private LocalDate publishedDate;
    @NotNull(message = "Field price cannot be blank or empty")
    private double price;
    @NotBlank(message = "Field image cannot be blank or empty")
    private String image;
    @NotBlank(message = "Field description cannot be blank or empty")
    private String description;
}
