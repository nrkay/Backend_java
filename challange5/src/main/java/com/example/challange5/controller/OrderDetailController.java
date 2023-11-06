package com.example.challange5.controller;


import com.example.challange5.model.OrderDetail;
import com.example.challange5.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<OrderDetail> create(@RequestHeader("idProduct")UUID id,
                                              @RequestHeader("idOrder") UUID idOrder,
                                              @RequestBody OrderDetail request){
        OrderDetail response = orderDetailService.create(idOrder, id, request);
        if (Objects.nonNull(response)){
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        OrderDetail respon = orderDetailService.remove(id);
        if (Objects.nonNull(respon)){
            return ResponseEntity.ok("Delete Success");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> update(@PathVariable("id") UUID id,
                                              @RequestBody OrderDetail request){
        OrderDetail respon = orderDetailService.update(id, request);
        if (Objects.nonNull(respon)){
            return ResponseEntity.ok(respon);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
