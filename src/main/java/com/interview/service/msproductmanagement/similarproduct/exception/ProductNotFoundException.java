package com.interview.service.msproductmanagement.similarproduct.exception;


public class ProductNotFoundException extends Exception{

  private static final long serialVersionUID = 1L;
  private String message = "Product not found";

  public ProductNotFoundException() {
  }
  public ProductNotFoundException(String message) {
    this.message = message;
  }
  public String getMessage() {
    return message;
  }
}