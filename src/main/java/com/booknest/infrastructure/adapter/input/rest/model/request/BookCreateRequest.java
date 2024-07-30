package com.booknest.infrastructure.adapter.input.rest.model.request;

import com.booknest.domain.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @NotEmpty(message = "Field genres cannot be empty")
    private Set<String> genres;
    @NotNull(message = "Field image cannot be blank or empty")
    private String image;
    public Set<Genre> getGenresEnumSet() {
        if (genres == null) {
            return new HashSet<>();
        }
        return genres.stream()
                .map(Genre::valueOf)  // Convierte cada String a Genre
                .collect(Collectors.toSet());
    }
}
