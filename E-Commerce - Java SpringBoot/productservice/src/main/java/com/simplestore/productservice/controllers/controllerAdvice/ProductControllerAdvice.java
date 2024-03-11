package com.simplestore.productservice.controllers.controllerAdvice;

import com.simplestore.productservice.exceptions.InvalidTitleException;
import com.simplestore.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(Exception ex) {

        String exceptionResponse = ex.getMessage();
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidTitleException.class)
    public ResponseEntity handleInvalidTitleException(Exception ex) {

        String exceptionResponse = ex.getMessage();
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
