package com.scaler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponse>handleOutOfStockException(OutOfStockException outOfStockException){
        ErrorResponse errorResponse = new ErrorResponse(
                outOfStockException.getMessage() ,
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse(
                productNotFoundException.getMessage() ,
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
