package com.example.ordermerchant.controller;

import com.example.ordermerchant.dto.Product.ProductRequest;
import com.example.ordermerchant.dto.Product.ProductResponse;
import com.example.ordermerchant.dto.ResponHandler;
import com.example.ordermerchant.model.Product;
import com.example.ordermerchant.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/merchant/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/message")
    public String message(){
        return "test from product controller";
    }

    //find All Product
    @GetMapping("/All")
    public List<ProductResponse> allProduct(){
        return productService.findAllProduct();
    }
    @PostMapping("/{id}")
    public ResponseEntity<Object> addData(@PathVariable("id")UUID id, @RequestBody ProductRequest request){
        Optional<ProductResponse> response = Optional.ofNullable(productService.addProduct(id, request));
        if (Objects.nonNull(response)){
            return ResponHandler.responsePost("Successfuly Create Data", HttpStatus.OK, response);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/orderCompleted/{id_product}")
    public ResponseEntity<Object> findOrderCompletedByProduct(@PathVariable("id_product") UUID id){
        return ResponHandler.responsePost("Get Data", HttpStatus.OK, productService.findAllOrderCompletedByProduct(id));
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id){
        Product response = productService.findById(id);
        return ResponHandler.responsePost("Data is Found",
                HttpStatus.OK,
                modelMapper.map(response, ProductResponse.class));
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Object> editData(@PathVariable("id") UUID id, @RequestBody ProductRequest request){
        Product response = productService.editProduct(id,request);
        return ResponHandler.responsePost("Successfuly Edit Data",
                HttpStatus.OK,
                modelMapper.map(response, ProductResponse.class));
    }

    //delete data
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete data is successfuly with id :" + id);
    }

}
