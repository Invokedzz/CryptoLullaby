package org.cryptolullaby.exception;

public class PostNotFoundException extends ResourceNotFoundException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
