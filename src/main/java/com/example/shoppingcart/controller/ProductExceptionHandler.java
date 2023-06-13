package com.example.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sorry something went wrong" +e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleFieldException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request type. Try again later!" +e.getMessage());
    }
}
