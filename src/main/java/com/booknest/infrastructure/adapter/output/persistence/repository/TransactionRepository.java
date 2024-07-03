package com.booknest.infrastructure.adapter.output.persistence.repository;

import com.booknest.infrastructure.adapter.output.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findByStockEntityId(UUID id);
}
