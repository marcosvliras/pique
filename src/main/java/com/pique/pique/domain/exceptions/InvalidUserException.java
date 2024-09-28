package com.pique.pique.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserException extends ExceptionInterface{
    public InvalidUserException (String message){
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
