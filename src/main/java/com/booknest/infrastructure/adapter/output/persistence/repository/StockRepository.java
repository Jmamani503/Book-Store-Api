package com.booknest.infrastructure.adapter.output.persistence.repository;

import com.booknest.infrastructure.adapter.output.persistence.entity.StockEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<StockEntity, UUID> {
    Optional<StockEntity> findByBookEntityId(UUID bookId);

}
