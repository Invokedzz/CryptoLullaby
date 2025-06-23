package org.cryptolullaby.exception;

public class UserNotCreatedInKeycloakException extends BadRequestException {
  public UserNotCreatedInKeycloakException(String message) {
    super(message);
  }
}
