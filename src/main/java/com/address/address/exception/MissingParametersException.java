package com.address.address.exception;

import org.springframework.http.HttpStatus;

public class MissingParametersException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public MissingParametersException(String message){
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
