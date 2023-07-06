package com.party_grouping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleDnfApiException(ApiException ex) {
        String message = null;
        String code = ex.getCode();
        int status = ex.getStatus();

        if (ex.getErrorCode() != null) {
            message = ex.getMessage();
        }
        ex.printStackTrace();

        return new ResponseEntity<>(message, HttpStatusCode.valueOf(status));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> defaultExceptionHandler(Exception ex) {
        System.out.println("범용에러");
        ex.printStackTrace();

        return ResponseEntity.internalServerError().build();
    }
}
