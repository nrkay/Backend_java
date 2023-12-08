package com.example.orderservice.controller;

import com.example.orderservice.DTO.Order.OrderRequest;
import com.example.orderservice.DTO.Order.OrderResponse;
import com.example.orderservice.DTO.ResponHandler;
import com.example.orderservice.Model.Order;
import com.example.orderservice.Service.OrderrService;
import com.example.orderservice.client.dto.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderrService orderrService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/message")
    public String message(){
        return "test from order service";
    }

    @PostMapping("/")
    public ResponseEntity<Object> addData(@RequestBody OrderRequest request){
        Optional<OrderResponse> response = Optional.ofNullable(orderrService.addOrder(request));
        if (response.isPresent()){
            return ResponHandler.responsePost("Successfuly Create Data", HttpStatus.OK, response);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //findById
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Order response = orderrService.findById(id);
        return ResponHandler.responsePost("Data is Found",
                HttpStatus.OK,
                modelMapper.map(response, OrderResponse.class));
    }

    //edit order
    @PutMapping("/{id}")
    public ResponseEntity<Object> editData(@PathVariable UUID id, @RequestBody OrderRequest orderRequest){
        Order response = orderrService.editProduct(id, orderRequest);
        return ResponHandler.responsePost("Successfully Edit Data",
                HttpStatus.OK,
                modelMapper.map(response, OrderResponse.class));
    }

    //delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable UUID id){
        orderrService.deleteOrder(id);
        return ResponseEntity.ok("Delete data is successfuly with id :" + id);
    }

    //get all product from product service
    @GetMapping("/product")
    public ResponseEntity<Object> getAllProduct(){
        return ResponHandler.responsePost("Successfuly Get Product From Product Service", HttpStatus.OK, orderrService.getAllProduct());
    }

    //chackout data
    @PutMapping("/chackout/{id}")
    public ResponseEntity<Object> chackOutData(@PathVariable UUID id){
        return ResponHandler.responsePost("Chackout is success",
                HttpStatus.OK,
                orderrService.chackOut(id));
    }


}
