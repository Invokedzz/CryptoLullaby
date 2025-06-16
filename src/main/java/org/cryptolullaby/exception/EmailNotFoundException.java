package org.cryptolullaby.exception;

public class EmailNotFoundException extends ResourceNotFoundException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}
