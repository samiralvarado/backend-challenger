package com.tenpo.challenge_backend.exception;

public class InvalidCalculationException extends RuntimeException {

    public InvalidCalculationException(String message) {
        super(message);
    }
}
