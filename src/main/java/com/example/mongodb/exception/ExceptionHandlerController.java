package com.example.mongodb.exception;

import com.example.mongodb.controller.ToeicTestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

//    @ExceptionHandle(value = ToeicTestController.class)
//    public ResponseEntity<Object> exception(ProductNotfoundException exception) {
//        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
//    }
}
