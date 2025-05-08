package org.cryptolullaby.exception;

public class DivergentPasswordsException extends BadRequestException {
    public DivergentPasswordsException(String message) {
        super(message);
    }
}
