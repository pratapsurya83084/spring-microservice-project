package com.address.address.config;

public class CustomException extends RuntimeException {

    private int status;

    public CustomException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}