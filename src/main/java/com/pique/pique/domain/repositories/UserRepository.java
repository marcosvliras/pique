package com.pique.pique.domain.repositories;

import com.pique.pique.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByDocumentAndEmail(String document, String email);
}
