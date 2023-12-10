package com.example.revisichallange3.dto.responseHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponHandler {
    public static ResponseEntity<Object> responsePost(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", HttpStatus.OK);
        map.put("data", responseObj);
        return new ResponseEntity<Object>(map, status);
    }
}
