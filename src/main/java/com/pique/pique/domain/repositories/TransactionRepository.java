package com.pique.pique.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pique.pique.domain.entities.TransactionEntity;


public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
