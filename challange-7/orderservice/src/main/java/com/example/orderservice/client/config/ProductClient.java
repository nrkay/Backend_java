package com.example.orderservice.client.config;

import com.example.orderservice.client.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "merchant-service", url = "http://localhost:8222/api/v1/merchant/product")
public interface ProductClient {
    @GetMapping("/All")
    List<Product> listProduct();
}
