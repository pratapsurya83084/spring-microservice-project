package com.employeeService.employeeService.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //  Use = is used in Spring Boot for global exception handling and global response handling for REST APIs.
// Main use = It catches exceptions from all controllers in one place.


public class GlobelExceptionHandler  extends com.commonLibraryService.commonService.exception.GlobelExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    ResponseEntity<ErrorResponse>  handelResourceNotfoundException(ResourceNotFoundException ex){
//        ErrorResponse  response = new ErrorResponse(ex.getMessage() , ex.getStatus().toString());
//        return  new ResponseEntity<>(response,ex.getStatus());
//    }
//
//
//
//    @ExceptionHandler(BadRequestException.class)
//    ResponseEntity<ErrorResponse>  handelBadRequestException(ResourceNotFoundException ex){
//          ErrorResponse  response = new ErrorResponse(ex.getMessage() , ex.getStatus().toString());
//          return  new ResponseEntity<>(response,ex.getStatus());
//    }
//
//    @ExceptionHandler(MissingParametersException.class)
//    ResponseEntity<ErrorResponse>  handelMissingParametersException(MissingParametersException ex){
//        ErrorResponse  response = new ErrorResponse(ex.getMessage() , ex.getStatus());
//        return  new ResponseEntity<>(response,ex.getStatus().toString());
//    }

}
