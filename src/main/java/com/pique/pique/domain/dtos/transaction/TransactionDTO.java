package com.pique.pique.domain.dtos.transaction;

public record TransactionDTO(
        Long senderId,
        Long receiverId,
        Double value
) {
}
