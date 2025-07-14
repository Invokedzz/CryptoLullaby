package org.cryptolullaby.exception;

public class DirectoryTraversalException extends BadRequestException {
    public DirectoryTraversalException(String message) {
        super(message);
    }
}
