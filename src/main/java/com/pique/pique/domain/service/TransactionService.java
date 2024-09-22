package com.pique.pique.domain.service;

import com.pique.pique.domain.dtos.transaction.TransactionDTO;
import com.pique.pique.domain.entities.TransactionEntity;
import com.pique.pique.domain.entities.UserEntity;
import com.pique.pique.domain.entities.enums.UserType;
import com.pique.pique.domain.exceptions.InvalidTransactionException;
import com.pique.pique.domain.exceptions.UserAlreadyExistsException;
import com.pique.pique.domain.exceptions.UserNotFoundException;
import com.pique.pique.domain.repositories.TransactionRepository;
import com.pique.pique.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public TransactionEntity createTransaction(TransactionDTO transactionDTO){
        UserEntity sender = validateSender(transactionDTO.senderId());
        UserEntity receiver = validateReceiver(transactionDTO.receiverId());
        TransactionEntity newTransaction = new TransactionEntity(
                sender,
                receiver,
                transactionDTO.value()
        );
        var r = transactionRepository.save(newTransaction);
        return r;
    }

    private UserEntity validateSender(Long senderId) {
        var optionalSender = userRepository.findById(senderId);
        if (optionalSender.isEmpty()) {
            throw new UserNotFoundException("Sender does not exists.");
        }
        UserEntity sender = optionalSender.get();

        if (sender.getUserType().equals(UserType.PJ)){
            throw new InvalidTransactionException("Invalid transaction. Sender must not be a CPF.");
        }

        return sender;
    }

    private UserEntity validateReceiver(Long receiverId) {
        var optionalReceiver = userRepository.findById(receiverId);
        if (optionalReceiver.isEmpty()) {
            throw new UserNotFoundException("Receiver does not exists.");
        }
        return optionalReceiver.get();
    }
}
