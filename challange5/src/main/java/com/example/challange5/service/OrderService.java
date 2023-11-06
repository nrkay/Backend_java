package com.example.challange5.service;


import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.CustomerUser;
import com.example.challange5.repository.CustomerOrderRepository;
import com.example.challange5.repository.CustomerUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerUserRepository customerUserRepository;

    public CustomerOrder create(UUID id, CustomerOrder request){
        Optional<CustomerUser> user = customerUserRepository.findById(id);
        if (user.isPresent()){
            if (user.get().getDeleted().equals(false)){
                CustomerOrder orderCreate = CustomerOrder.builder()
                        .completed(false)
                        .user(user.get())
                        .orderTime(LocalDate.now())
                        .location(request.getLocation())
                        .build();
                log.info("order create : {}", orderCreate);
                return customerOrderRepository.save(orderCreate);
            } else {
                log.info("user was deleted");
                return null;
            }
        } else {
            log.info("user not found");
            return null;
        }
    }

    public CustomerOrder remove(UUID id){
        Optional<CustomerOrder> order = customerOrderRepository.findById(id);
        if (order.isPresent()){
            if (order.get().getDeleted().equals(false)){
                CustomerOrder customerOrderRespon = order.get();
                customerOrderRepository.deleteById(id);
                log.info("Delete Success");
                return customerOrderRespon;
            } else {
                log.info("Data was Deleted");
                return null;
            }
        } else {
            log.info("Data not found");
            return null;
        }
    }

    public CustomerOrder update(UUID id, CustomerOrder request){
        Optional<CustomerOrder> order = customerOrderRepository.findById(id);
        if (order.isPresent()){
            if (order.get().getDeleted().equals(false)){
                order.get().setLocation(request.getLocation());
                log.info("update order: {}", order);
                return customerOrderRepository.save(order.get());
            } else {
                log.info("data was deleted");
                return null;
            }
        } else {
            log.info("data not found");
            return null;
        }
    }
}
