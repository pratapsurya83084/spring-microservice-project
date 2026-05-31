//package com.employeeService.employeeService.config;
//
//import com.employeeService.employeeService.exception.CustomException;
//import com.employeeService.employeeService.exception.ErrorResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.Response;
//import feign.codec.ErrorDecoder;
//
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class CustomErrorDecoder implements ErrorDecoder{
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try (InputStream is = response.body().asInputStream()) {
//
//            ErrorResponse errorResponse =
//                    objectMapper.readValue(is, ErrorResponse.class);
//
//            return new CustomException(
//                    errorResponse.getMessage(),
//                    errorResponse.getHttpStatus()
//            );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new CustomException(
//                    "INTERNAL_SERVER_ERROR",
//                    "500"
//            );
//        }
//    }
//}
//
//
//
//
//
//
//
////
////
////package com.employeeService.employeeService.config;
////
////import com.employeeService.employeeService.exception.CustomException;
////import com.employeeService.employeeService.exception.ErrorResponse;
////import com.fasterxml.jackson.databind.ObjectMapper;
////import feign.Response;
////import feign.codec.ErrorDecoder;
////
////import java.io.IOException;
////import java.io.InputStream;
////
////public class CustomErrorDecoder implements ErrorDecoder {
////
////    @Override
////    public Exception decode(String methodKey, Response response) {
////
////        ObjectMapper objectMapper = new ObjectMapper();
////
////        try {
////
////            if (response.body() == null) {
////
////                return new CustomException(
////                        "Response body is null",
////                        response.status()
////                );
////            }
////
////            InputStream is = response.body().asInputStream();
////
////            ErrorResponse errorResponse =
////                    objectMapper.readValue(is, ErrorResponse.class);
////
////            return new CustomException(
////                    errorResponse.getMessage(),
////                    errorResponse.getHttpStatus().value()
////            );
////
////        } catch (IOException e) {
////
////            e.printStackTrace();
////
////            return new CustomException(
////                    e.getMessage(),
////                    e.get
////            );
////        }
////    }
////}




//
//
//
//package com.employeeService.employeeService.config;
//
//import com.employeeService.employeeService.exception.CustomException;
////import com.employeeService.employeeService.exception.ErrorResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import feign.Response;
//import feign.codec.ErrorDecoder;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class CustomErrorDecoder implements ErrorDecoder {
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        // IMPORTANT
//        objectMapper.registerModule(new JavaTimeModule());
//
//        try (InputStream is = response.body().asInputStream()) {
//
//            ErrorResponse errorResponse =
//                    objectMapper.readValue(is, ErrorResponse.class);
//            return new CustomException(
//                    errorResponse.getMessage(),
//                    response.toString()
//            );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new CustomException(
//                    "INTERNAL_SERVER_ERROR",
//                    "500"
//            );
//        }
//    }
//}