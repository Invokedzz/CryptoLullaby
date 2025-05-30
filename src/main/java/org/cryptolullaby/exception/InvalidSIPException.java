package org.cryptolullaby.exception;

public class InvalidSIPException extends BadRequestException {
    public InvalidSIPException(String message) {
        super(message);
    }
}
