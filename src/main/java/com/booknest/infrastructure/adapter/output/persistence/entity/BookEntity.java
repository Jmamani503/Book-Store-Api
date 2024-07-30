package com.booknest.infrastructure.adapter.output.persistence.entity;

import com.booknest.domain.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String author;
    private String image;
    @ElementCollection
    private Set<String> genres;
    @OneToMany(
            mappedBy = "bookEntity",
            fetch = FetchType.LAZY
    )
    private List<StockEntity> stockEntities;
    public Set<Genre> getGenreEnumSet() {
        if (genres == null) {
            return new HashSet<>();
        }
        return genres.stream()
                .map(Genre::valueOf)  // Convierte cada String a Genre
                .collect(Collectors.toSet());
    }

    public void setGenreEnumSet(Set<Genre> genres) {
        this.genres = genres.stream()
                .map(Enum::name)  // Convierte cada Genre a su nombre como String
                .collect(Collectors.toSet());
    }
}
