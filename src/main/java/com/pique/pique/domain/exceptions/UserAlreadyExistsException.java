package com.pique.pique.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ExceptionInterface {

    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
