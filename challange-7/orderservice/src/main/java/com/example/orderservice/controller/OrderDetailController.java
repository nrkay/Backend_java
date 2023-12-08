package com.example.orderservice.controller;

import com.example.orderservice.DTO.Order.OrderCompleted;
import com.example.orderservice.DTO.OrderDetail.OrderDetailRequest;
import com.example.orderservice.DTO.OrderDetail.OrderDetailResponse;
import com.example.orderservice.DTO.ResponHandler;
import com.example.orderservice.Service.OrderDetailService;
import com.example.orderservice.advice.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;
    @GetMapping("/message")
    public String message(){
        return "test from order detail controller";
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> addOrderDetail(@PathVariable("id") UUID idOrder,
                                                 @RequestBody OrderDetailRequest request){
        OrderDetailResponse response = orderDetailService.addOrder(idOrder, request);
        if (Objects.nonNull(response)){
            return ResponHandler.responsePost("Successfuly Create Data", HttpStatus.OK, response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //editData
    @PutMapping("/{id}")
    public ResponseEntity<Object> editData(@PathVariable UUID id, @RequestBody OrderDetailRequest request){
        return ResponHandler.responsePost("Successfully Edit Data",
                HttpStatus.OK,
                orderDetailService.editData(id, request));
    }

    //findByIdWithName
    @GetMapping("/recordOrderDetail/{id}")
    public ResponseEntity<Object> findByIdWithNameProduct(@PathVariable UUID id){
        return ResponHandler.responsePost("Data is Found", HttpStatus.OK,
                orderDetailService.findByIdWithNameProduct(id));
    }

    //deleteData
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable UUID id){
        orderDetailService.deleteProduct(id);
        return ResponseEntity.ok("Delete data is successfuly with id :" + id);
    }

    //to get order is completed
    @GetMapping("/orderCompleted/{id_product}")
    public List<OrderCompleted> orderCompletedByProduct(@PathVariable UUID id_product){
        List<OrderCompleted> x = orderDetailService.orderCompletedByProduct(id_product);
        if (Objects.isNull(x)){
            throw new DataNotFoundException("Data Not Found");
        } else {
            return x;
        }
    }
}
