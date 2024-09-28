package com.pique.pique.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTransactionException extends ExceptionInterface{
    public InvalidTransactionException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
