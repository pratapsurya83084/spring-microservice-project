package com.address.address.exception;


import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public BadRequestException(String message){
        super(message);
        this.message = message;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    //geetrs

    public String getMessage(){
        return message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
