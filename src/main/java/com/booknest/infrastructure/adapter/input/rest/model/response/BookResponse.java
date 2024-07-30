package com.booknest.infrastructure.adapter.input.rest.model.response;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {
    private UUID id;
    private String title;
    private String author;
    private String image;
    private Set<String> genres;
}
