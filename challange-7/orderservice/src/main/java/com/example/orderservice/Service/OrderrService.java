package com.example.orderservice.Service;

import com.example.orderservice.DTO.Order.ChackOutResponse;
import com.example.orderservice.DTO.Order.OrderRequest;
import com.example.orderservice.DTO.Order.OrderResponse;
import com.example.orderservice.DTO.ResponHandler;
import com.example.orderservice.Model.Order;
import com.example.orderservice.Model.OrderDetail;
import com.example.orderservice.Repository.OrderDetailRepository;
import com.example.orderservice.Repository.OrderRepository;
import com.example.orderservice.advice.exception.DataNotFoundException;
import com.example.orderservice.client.config.ProductClient;
import com.example.orderservice.client.dto.Product;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderrService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    //add order
    public OrderResponse addOrder(OrderRequest orderRequest){
        Order request = Order.builder()
                .completed(false)
                .orderTime(LocalDate.now())
                .id_user(orderRequest.getId_user())
                .location(orderRequest.getLocation())
                .build();
        Order requestData = orderRepository.save(request);
        return modelMapper.map(requestData, OrderResponse.class);
    }

    //findById
    public Order findById(UUID id){
        return orderRepository.findById(id).
                orElseThrow(() -> new DataNotFoundException("Data Not Found with id: " + id));
    }

    //editProduct
    public Order editProduct(UUID id, OrderRequest request){
        var existData = findById(id);
        existData.setLocation(request.getLocation());
        return orderRepository.save(existData);
    }

    //delete data
    public void deleteOrder(UUID id){
        var dataExist = findById(id);
        orderRepository.delete(dataExist);
    }

    //getProduct
    public List<Product> getAllProduct(){
        return  productClient.listProduct();
    }

    //chackout order

    @Transactional
    public ChackOutResponse chackOut(UUID id){
        var existData = findById(id);
        Long totalPrice = 0L;
        List<OrderDetail> listOrderDetail = orderDetailRepository.findByOrder(existData);
        if (listOrderDetail.isEmpty()){
            new DataNotFoundException("No Food Ordered");
        } else {
            for (OrderDetail o: listOrderDetail){
                totalPrice += o.getTotalPrice();
            }
            existData.setCompleted(true);
            existData.setComplated_at(LocalDate.now());
            existData.setTotalPrice(totalPrice);
        }
        orderRepository.save(existData);
        return modelMapper.map(existData, ChackOutResponse.class);
    }





}
