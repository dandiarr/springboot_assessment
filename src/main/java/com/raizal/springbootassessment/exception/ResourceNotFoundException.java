package com.raizal.springbootassessment.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource Not Found. Please try again.");
    }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
