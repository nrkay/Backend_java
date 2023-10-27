package com.example.demo.service;

import com.example.demo.model.DTO.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;


    //disebut constructor injection
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getOpenProductMerchant() {
        return productRepository.findOpenProductMerchant();
    }

    @Transactional
    @Override
    public void deleteProduct(String productName) {
         productRepository.deleteProduct(productName);
    }


    @Override
    public Product findProductById() {
        return null;
    }



}
