package com.pique.pique.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ExceptionInterface {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
