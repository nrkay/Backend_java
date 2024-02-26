package com.dummy_service_2.dummy_service_2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/service2")
public class DummyService2Controller {
    @GetMapping("/transaction")
    public ResponseEntity<Object> getById() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "User available");
        map.put("status", HttpStatus.OK.value());
        map.put("message", "This is list of transactions of service 2");
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
}
