package com.Authmicroservice.Authmicroservice.exception;

public class CustomException extends RuntimeException {

    private String status;

    public CustomException(String message, String status) {
        super(message);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}