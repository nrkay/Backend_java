package com.example.revisichallange3.controller;

import com.example.revisichallange3.dto.merchant.MerchantRequest;
import com.example.revisichallange3.dto.merchant.MerchantResponse;
import com.example.revisichallange3.dto.responseHandler.ResponHandler;
import com.example.revisichallange3.model.Orders;
import com.example.revisichallange3.service.MerchantService;
import com.example.revisichallange3.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private ModelMapper modelMapper;

    //add
    @PostMapping("/{id}")
    public ResponseEntity<Object> createData (@PathVariable("id") UUID id, @RequestBody Orders request){
        Optional<Orders> response = Optional.ofNullable(service.create(id, request));
        if (response.isPresent()){
            return ResponHandler.responsePost("Successfully Add Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") UUID id, @RequestBody Orders request){
        Optional<Orders> response = Optional.ofNullable(service.update(id, request));
        if(response.isPresent()){
            return ResponHandler.responsePost("Successfully Edit Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable("id") UUID id){
        service.remove(id);
        return ResponHandler.responsePost("Successfully Delete Data", HttpStatus.OK, "delete data id :");
    }
}
