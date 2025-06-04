package org.cryptolullaby.exception;

public class InvalidPostConditionException extends BadRequestException {
    public InvalidPostConditionException(String message) {
        super(message);
    }
}
