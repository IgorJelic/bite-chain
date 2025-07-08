package com.bitechain.menuservice.exception;

public class RestaurantNotFoundException extends RuntimeException {
  public RestaurantNotFoundException(String message) {
    super(message);
  }
}
