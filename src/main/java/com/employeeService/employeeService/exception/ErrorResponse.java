//package com.employeeService.employeeService.exception;
//
//
//
//import java.time.LocalDateTime;
//
//public class ErrorResponse {
//    private String message;
//    private String httpStatus;
//    private String timeStamp;
//
//    public ErrorResponse() {
//    }
//
//    public ErrorResponse(String message , String httpStatus ,String timeStamp){
//
//        this.message = message;
//        this.httpStatus = httpStatus;
//        this.timeStamp = timeStamp; // current running time
//    }
//
//    public ErrorResponse(String message, String httpStatus) {
//    this.message = message;
//    this.httpStatus = httpStatus;
//    }
//
//    public String getTimeStamp() {
//        return timeStamp;
//    }
//
//    public void setTimeStamp(String timeStamp) {
//        this.timeStamp = timeStamp;
//    }
//
//    public String getHttpStatus() {
//        return httpStatus;
//    }
//
//    public void setHttpStatus(String httpStatus) {
//        this.httpStatus = httpStatus;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public  String getStatus(){
//        return httpStatus;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//}
