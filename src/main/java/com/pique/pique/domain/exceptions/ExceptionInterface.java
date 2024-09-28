package com.pique.pique.domain.exceptions;

public class ExceptionInterface extends RuntimeException {

    private int statusCode;

    public ExceptionInterface(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
