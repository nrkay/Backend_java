package com.khairiyah.revisichallange4.service;
import com.khairiyah.revisichallange4.model.Orders;
import com.khairiyah.revisichallange4.model.User;
import com.khairiyah.revisichallange4.repository.OrderDetailRepository;
import com.khairiyah.revisichallange4.repository.OrdersRepository;
import com.khairiyah.revisichallange4.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Orders create(UUID id, Orders request){
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()){
            if (user.get().getDeleted().equals(false)){
                Orders orderCreate = Orders.builder()
                        .completed(false)
                        .user(user.get())
                        .orderTime(LocalDate.now())
                        .location(request.getLocation())
                        .build();
                return ordersRepository.save(orderCreate);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Orders remove(UUID id){
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()){
            if (order.get().getDeleted().equals(false)){
                Orders customerOrderRespon = order.get();
                ordersRepository.deleteById(id);
                return customerOrderRespon;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Orders update(UUID id, Orders request){
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()){
            if (order.get().getDeleted().equals(false)){
                order.get().setLocation(request.getLocation());
                return ordersRepository.save(order.get());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }




}
