package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Merchant;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public CustomerOrder findOrderById(UUID customerOrderID){
        Optional<CustomerOrder> optionalOrder = orderRepository.findById(customerOrderID);
        if (optionalOrder.isPresent()){
            return optionalOrder.get();
        } else {
            throw new EntityNotFoundException("Merchant not found with ID: " + optionalOrder);
        }
    }

    public CustomerOrder create(CustomerOrder order){
        order.setCompleted(false);
        return orderRepository.save(order);
    }

    @Transactional
    public void updateStatus(UUID orderID){
        orderRepository.updateCompletedStatusByUser(orderID);
    }

    public Product getProduct(String productName){
        return orderRepository.findProductByName(productName);
    }
}
