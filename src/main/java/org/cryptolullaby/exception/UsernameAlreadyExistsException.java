package org.cryptolullaby.exception;

public class UsernameAlreadyExistsException extends BadRequestException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
