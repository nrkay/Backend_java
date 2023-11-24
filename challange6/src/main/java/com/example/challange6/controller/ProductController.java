package com.example.challange6.controller;


import com.example.challange6.model.Product;
import com.example.challange6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/Product")
public class ProductController {

    @Autowired
    ProductService productService;

    //1. endpoint public
    @GetMapping("/active")
//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<Product>> getActiveProduct(){
        List<Product> activeProduct = productService.getActive();
        return new ResponseEntity<>(activeProduct, HttpStatus.OK);
    }

    @GetMapping("/active2")
    public ResponseEntity<List<Product>> getPorduct(){
        List<Product> activeProduct = productService.getActive();
        return new ResponseEntity<>(activeProduct, HttpStatus.OK);
    }

    //2. endpoint private (auth)
}
