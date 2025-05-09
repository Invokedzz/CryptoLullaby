package org.cryptolullaby.exception;

public class PayloadTooLargeException extends RuntimeException {
    public PayloadTooLargeException(String message) {
        super(message);
    }
}
