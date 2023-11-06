package com.example.challange5.controller;


import com.example.challange5.model.CustomerOrder;
import com.example.challange5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {
    @Autowired
    private OrderService orderService;


    //get user by id


    @PostMapping
    public CustomerOrder create(@RequestBody CustomerOrder customerOrder, @RequestHeader UUID id){
        return orderService.create(id, customerOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        CustomerOrder respon = orderService.remove(id);
        if (respon != null){
            return ResponseEntity.ok("Delete Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerOrder> update(@PathVariable("id") UUID id, @RequestBody CustomerOrder request){
        CustomerOrder respon = orderService.update(id, request);
        if (respon != null){
            return ResponseEntity.ok(respon);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
