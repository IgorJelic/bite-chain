package com.bitechain.userservice.exception;

public class UserConflictException extends RuntimeException {
  public UserConflictException(String message) {
    super(message);
  }
}
