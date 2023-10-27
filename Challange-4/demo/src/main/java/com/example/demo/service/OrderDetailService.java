package com.example.demo.service;

import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    public OrderDetail create(OrderDetail orderDetail){
        return orderDetailRepository.save(orderDetail);
    }

//    public List<OrderDetail> getAllOrderProduct(UUID idOrder){
//        return orderDetailRepository.findByOrderId(idOrder);
//    }
//
//    public void getTotalPrice(UUID orderID){
//        Long totalPrice = orderDetailRepository.calculateTotalPriceByOrderId(orderID);
//        if (totalPrice != null) {
//            System.out.println("Total Harga untuk Order " + orderID + ": " + totalPrice);
//        } else {
//            System.out.println("Tidak ada data OrderDetail yang sesuai dengan Order ID " + orderID);
//        }
//    }

}
