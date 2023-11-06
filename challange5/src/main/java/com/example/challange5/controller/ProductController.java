package com.example.challange5.controller;


import com.example.challange5.model.Merchant;
import com.example.challange5.model.Product;
import com.example.challange5.model.dto.ProductRequest;
import com.example.challange5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestHeader("id") UUID id, @RequestBody Product product) {
        Product response = productService.create(id, product);
        if (Objects.nonNull(response)) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        productService.remove(id);
    }

   @PutMapping("/{id}")
   public ResponseEntity<Product> update(@PathVariable("id") UUID id, @RequestBody ProductRequest productRequest){
       Product response = productService.update(id, productRequest);
       if (response != null) {
           return ResponseEntity.ok(response);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }

}
