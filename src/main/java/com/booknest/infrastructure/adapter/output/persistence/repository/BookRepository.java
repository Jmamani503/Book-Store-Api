package com.booknest.infrastructure.adapter.output.persistence.repository;

import com.booknest.infrastructure.adapter.output.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
}
