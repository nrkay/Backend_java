package com.example.challange6.service;

import com.example.challange6.model.Product;
import com.example.challange6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    //constructor injection
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> getActive() {
        return productRepository.findAllByActive(true);
    }
}
