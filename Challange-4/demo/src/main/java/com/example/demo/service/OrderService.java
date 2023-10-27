package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public CustomerOrder create(CustomerOrder order){
        order.setCompleted(false);
        return orderRepository.save(order);
    }

    public Product getProduct(String productName){
        return orderRepository.findProductByName(productName);
    }
}
