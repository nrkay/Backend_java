package com.example.revisichallange3.controller;

import com.example.revisichallange3.dto.product.ProductRequest;
import com.example.revisichallange3.dto.product.ProductResponse;
import com.example.revisichallange3.dto.responseHandler.ResponHandler;
import com.example.revisichallange3.model.Product;
import com.example.revisichallange3.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    //add
    @PostMapping("/{id}")
    public ResponseEntity<Object> addData(@PathVariable("id") UUID id, @RequestBody ProductRequest request){
        Optional<ProductResponse> response = Optional.ofNullable(productService.addProduct(id, request));
        if (Objects.nonNull(response)){
            return ResponHandler.responsePost("Successfuly Create Data", HttpStatus.OK, response);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Object> editData(@PathVariable("id") UUID id, @RequestBody ProductRequest request){
        Product response = productService.editProduct(id,request);
        return ResponHandler.responsePost("Successfuly Edit Data",
                HttpStatus.OK,
                modelMapper.map(response, ProductResponse.class));
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete data is successfuly with id :" + id);
    }
}
