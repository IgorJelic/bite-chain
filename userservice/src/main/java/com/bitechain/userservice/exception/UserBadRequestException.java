package com.bitechain.userservice.exception;

public class UserBadRequestException extends RuntimeException {
  public UserBadRequestException(String message) {
    super(message);
  }
}
