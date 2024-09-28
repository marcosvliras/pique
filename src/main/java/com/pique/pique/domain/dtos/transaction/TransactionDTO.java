package com.pique.pique.domain.dtos.transaction;

import java.math.BigDecimal;

public record TransactionDTO(
        Long senderId,
        Long receiverId,
        BigDecimal value
) {
}
