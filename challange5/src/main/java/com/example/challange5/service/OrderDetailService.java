package com.example.challange5.service;


import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.CustomerUser;
import com.example.challange5.model.OrderDetail;
import com.example.challange5.model.Product;
import com.example.challange5.repository.CustomerOrderRepository;
import com.example.challange5.repository.CustomerUserRepository;
import com.example.challange5.repository.OrderDetailRepository;
import com.example.challange5.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderDetailService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    //findOrderDetailByOrderId

    //create data
    public OrderDetail create(UUID idOrder, UUID idProduct, OrderDetail request) {
        Optional<Product> getProduct = productRepository.findById(idProduct);
        Optional<CustomerOrder> getOrder = customerOrderRepository.findById(idOrder);
        if (getProduct.isPresent() && getOrder.isPresent()) {
            if (getProduct.get().getDeleted().equals(false) && getOrder.get().getDeleted().equals(false)) {
                //logic berhasil
                //get totalPrice
                long totalPrice = request.getQuantity() * getProduct.get().getPrice();
                log.info("getQuantity : {}, getPrice: {}, totalPrice : {}", request.getQuantity(), getProduct.get().getPrice(), totalPrice);
                OrderDetail orderDetailCreate = OrderDetail.builder()
                        .product(getProduct.get())
                        .customerOrder(getOrder.get())
                        .quantity(request.getQuantity())
                        .totalPrice(totalPrice)
                        .build();
                return orderDetailRepository.save(orderDetailCreate);
            } else {
                log.info("data is error");
                return null;
            }
        } else {
            log.info("data not found");
            return null;
        }
    }

    //delete data
    public OrderDetail remove(UUID id){
        Optional<OrderDetail> respon = orderDetailRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                OrderDetail orderRespon = respon.get();
                orderDetailRepository.deleteById(id);
                return orderRespon;
            } else {
                log.info("data was deleted");
                return null;
            }
        } else {
            log.info("Data not found");
            return null;
        }
    }

    public OrderDetail update(UUID id, OrderDetail request){
        Optional<OrderDetail> respon = orderDetailRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                long totalPrice = respon.get().getProduct().getPrice() * request.getQuantity();
                log.info("total price : {}", totalPrice);
                respon.get().setQuantity(request.getQuantity());
                respon.get().setTotalPrice(totalPrice);
                orderDetailRepository.save(respon.get());
                return respon.get();
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

