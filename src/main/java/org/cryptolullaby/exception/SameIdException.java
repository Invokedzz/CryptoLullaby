package org.cryptolullaby.exception;

public class SameIdException extends BadRequestException {
    public SameIdException(String message) {
        super(message);
    }
}
