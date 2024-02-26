package com.gateway_service.gateway_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gateway_service.gateway_service.dto.ApiResponseDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/microservices")
@Slf4j
public class GatewayServiceController {
    @Autowired
    private RestTemplateBuilder restTemplate;

    @GetMapping("/service1")
    public ResponseEntity<Object> getService1() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer ");

        ApiResponseDto responseDto = restTemplate.build().getForObject("http://localhost:8082/api/service1/transaction",
                ApiResponseDto.class);

        log.info("response user  = " + responseDto);

        return new ResponseEntity<Object>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/service2")
    public ResponseEntity<Object> getService2() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer ");

        ApiResponseDto responseDto = restTemplate.build().getForObject("http://localhost:8083/api/service2/transaction",
                ApiResponseDto.class);

        log.info("response user  = " + responseDto);

        return new ResponseEntity<Object>(responseDto, HttpStatus.OK);
    }
}
