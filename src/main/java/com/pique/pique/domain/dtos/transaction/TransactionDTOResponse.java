package com.pique.pique.domain.dtos.transaction;

import com.pique.pique.domain.dtos.user.UserDTOResponse;
import com.pique.pique.domain.entities.TransactionEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTOResponse(
        UserDTOResponse senderId,
        UserDTOResponse receiverId,
        BigDecimal value,
        LocalDateTime timestamp
) {
    public static TransactionDTOResponse fromTransactionEntity(TransactionEntity transactionEntity) {
        return new TransactionDTOResponse(
                UserDTOResponse.fromUserEntity(transactionEntity.getSender()),
                UserDTOResponse.fromUserEntity(transactionEntity.getReceiver()),
                transactionEntity.getValue(),
                transactionEntity.getTimestamp()
        );
    }
}
