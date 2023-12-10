package com.khairiyah.revisichallange4.service;


import com.khairiyah.revisichallange4.model.OrderDetail;
import com.khairiyah.revisichallange4.model.Orders;
import com.khairiyah.revisichallange4.model.Product;
import com.khairiyah.revisichallange4.repository.OrderDetailRepository;
import com.khairiyah.revisichallange4.repository.OrdersRepository;
import com.khairiyah.revisichallange4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class OrderDetailService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail create(UUID idOrder, UUID idProduct, OrderDetail request) {
        Optional<Product> getProduct = productRepository.findById(idProduct);
        Optional<Orders> getOrder = ordersRepository.findById(idOrder);
        if (getProduct.isPresent() && getOrder.isPresent()) {
            if (getProduct.get().getDeleted().equals(false) && getOrder.get().getDeleted().equals(false)) {
                //logic berhasil
                //get totalPrice
                long totalPrice = request.getQuantity() * getProduct.get().getPrice();
                OrderDetail orderDetailCreate = OrderDetail.builder()
                        .product(getProduct.get())
                        .customerOrder(getOrder.get())
                        .quantity(request.getQuantity())
                        .totalPrice(totalPrice)
                        .build();
                return orderDetailRepository.save(orderDetailCreate);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public OrderDetail remove(UUID id){
        Optional<OrderDetail> respon = orderDetailRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                OrderDetail orderRespon = respon.get();
                orderDetailRepository.deleteById(id);
                return orderRespon;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public OrderDetail update(UUID id, OrderDetail request){
        Optional<OrderDetail> respon = orderDetailRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                long totalPrice = respon.get().getProduct().getPrice() * request.getQuantity();
                respon.get().setQuantity(request.getQuantity());
                respon.get().setTotalPrice(totalPrice);
                orderDetailRepository.save(respon.get());
                return respon.get();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
