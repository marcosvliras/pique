package com.pique.pique.domain.service;

import com.pique.pique.domain.dtos.transaction.TransactionDTO;
import com.pique.pique.domain.entities.TransactionEntity;
import com.pique.pique.domain.entities.UserEntity;
import com.pique.pique.domain.entities.enums.UserType;
import com.pique.pique.domain.exceptions.InvalidTransactionException;
import com.pique.pique.domain.exceptions.UserNotFoundException;
import com.pique.pique.domain.repositories.TransactionRepository;
import com.pique.pique.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionValidatorService transactionValidatorService;

    @Transactional
    public TransactionEntity executeTransaction(TransactionDTO transactionDTO){
        UserEntity sender = validateSender(transactionDTO);
        UserEntity receiver = validateReceiver(transactionDTO);

        TransactionEntity newTransaction = new TransactionEntity(
                sender,
                receiver,
                transactionDTO.value()
        );
        BigDecimal newSenderBalance = sender.getBalance().subtract(transactionDTO.value());
        sender.setBalance(newSenderBalance);

        BigDecimal newReceiverBalance = receiver.getBalance().add(transactionDTO.value());
        receiver.setBalance(newReceiverBalance);

        transactionValidatorService.validate(transactionDTO);
        userRepository.save(sender);
        userRepository.save(receiver);
        return transactionRepository.save(newTransaction);
    }

    private UserEntity validateSender(TransactionDTO transactionDTO) {
        var optionalSender = userRepository.findById(transactionDTO.senderId());
        if (optionalSender.isEmpty()) {
            throw new UserNotFoundException("Sender does not exists.");
        }
        UserEntity sender = optionalSender.get();

        if (sender.getUserType().equals(UserType.PJ)){
            throw new InvalidTransactionException("Invalid transaction, sender must be a CPF.");
        }

        validateSenderBalance(sender, transactionDTO.value());

        return sender;
    }

    private void validateSenderBalance(UserEntity sender, BigDecimal value) {
        if (sender.getBalance().compareTo(value) == -1) {
            throw new InvalidTransactionException("Insufficient balance for user " + sender.getId());
        }
    }

    private UserEntity validateReceiver(TransactionDTO transactionDTO) {
        var optionalReceiver = userRepository.findById(transactionDTO.receiverId());
        if (optionalReceiver.isEmpty()) {
            throw new UserNotFoundException("Receiver does not exists.");
        }

        if (transactionDTO.senderId() == transactionDTO.receiverId()) {
            throw new InvalidTransactionException("Invalid transaction, sender and receiver must be different.");
        }

        return optionalReceiver.get();
    }
}
