package com.commonLibraryService.commonService.exception;

public class MissingParametersException extends RuntimeException {

    private String message;
    private String status;

    public MissingParametersException(String message,String status){
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String  getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
