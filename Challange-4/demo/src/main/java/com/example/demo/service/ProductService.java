package com.example.demo.service;

import com.example.demo.model.DTO.ProductDTO;
import com.example.demo.model.Merchant;
import com.example.demo.model.Product;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getAll();
    Product create(Product product);
    List<Product> getOpenProductMerchant();
    @Transactional
    void deleteProduct(String productName);


    //@Transactional
    //Product updateProduct(Product product, UUID productId);
    Product findProductById();
}
