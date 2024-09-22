package com.pique.pique.domain.controllers;

import com.pique.pique.domain.dtos.transaction.TransactionDTO;
import com.pique.pique.domain.dtos.transaction.TransactionDTOResponse;
import com.pique.pique.domain.entities.TransactionEntity;
import com.pique.pique.domain.exceptions.ErrorResponse;
import com.pique.pique.domain.exceptions.InvalidTransactionException;
import com.pique.pique.domain.exceptions.UserNotFoundException;
import com.pique.pique.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionDTOResponse createTransaction(@RequestBody TransactionDTO transactionDTO){
         TransactionEntity transactionEntity = transactionService.createTransaction(transactionDTO);
         return TransactionDTOResponse.fromTransactionEntity(transactionEntity);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTransaction(InvalidTransactionException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
