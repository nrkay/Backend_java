package com.example.challange5.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
//    @ExceptionHandler(DataNotFound.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<String> handleNotFound(RuntimeException e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(DataNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<String> handleNotFound(RuntimeException e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
    public ResponseEntity<Map<String, String>> handleNotFound(RuntimeException e){
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("error", "Data Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }


}
