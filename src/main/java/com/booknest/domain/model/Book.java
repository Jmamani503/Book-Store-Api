package com.booknest.domain.model;

import com.booknest.domain.enums.Genre;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Book {
    private UUID id;
    private String title;
    private String author;
    private String image;
    private Set<Genre> genres;

    public Set<String> getStringGenres() {
        if (genres == null) {
            return new HashSet<>();
        }
        return genres.stream()
                .map(Enum::name)  // Convierte cada Genre a su nombre como String
                .collect(Collectors.toSet());
    }

    public void setStringGenres(Set<String> genres) {
        this.genres = genres.stream()
                .map(Genre::valueOf)  // Convierte cada String a Genre
                .collect(Collectors.toSet());
    }
}
