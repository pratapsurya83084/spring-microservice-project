package com.address.address.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder{

    @Override
    public Exception decode(String methodKey, Response response) {

        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream is = response.body().asInputStream()) {

            ErrorMessage errorResponse =
                    objectMapper.readValue(is, ErrorMessage.class);

            return new CustomException(
                    errorResponse.getMessage(),
                    errorResponse.getStatus()
            );

        } catch (IOException e) {

            return new CustomException(
                    "INTERNAL_SERVER_ERROR",
                    500
            );
        }
    }
}