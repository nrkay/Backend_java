package com.example.orderservice.Service;

import com.example.orderservice.DTO.Order.OrderCompleted;
import com.example.orderservice.DTO.Order.OrderRequest;
import com.example.orderservice.DTO.Order.OrderResponse;
import com.example.orderservice.DTO.OrderDetail.OrderDetailRequest;
import com.example.orderservice.DTO.OrderDetail.OrderDetailResponse;
import com.example.orderservice.Model.Order;
import com.example.orderservice.Model.OrderDetail;
import com.example.orderservice.Repository.OrderDetailRepository;
import com.example.orderservice.Repository.OrderRepository;
import com.example.orderservice.advice.exception.DataNotFoundException;
import com.example.orderservice.client.config.ProductClient;
import com.example.orderservice.client.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductClient productClient;

    //getProduct
    public List<Product> getAllProduct(){
        return  productClient.listProduct();
    }

    //addOrder
    public OrderDetailResponse addOrder(UUID idOrder, OrderDetailRequest request) {
        Long total = 0L;
        OrderDetail requestOrder = null;
        String nameProduct = null;
        OrderDetail saveData = null;
        List<Product> productAlready = getAllProduct();
        Optional<Order> checkAlreadyOrder = orderRepository.findById(idOrder);

        boolean productFound = false;

        for (Product e : productAlready) {
            if (request.getId_product().equals(e.getId())) {
                if (checkAlreadyOrder.isPresent()) {
                    total = e.getPrice() * request.getQuantity();
                    requestOrder = OrderDetail.builder()
                            .order(checkAlreadyOrder.get())
                            .quantity(request.getQuantity())
                            .totalPrice(total)
                            .id_product(request.getId_product())
                            .build();
                    nameProduct = e.getName();
                    saveData = orderDetailRepository.save(requestOrder);
                    productFound = true;
                    break;
                }
            }
        }
        if (!productFound) {
            throw new DataNotFoundException("Product Not Available");
        }

        if (!checkAlreadyOrder.isPresent()) {
            throw new DataNotFoundException("Order Not Available");
        }

        OrderDetailResponse orderDetailResponse = modelMapper.map(saveData, OrderDetailResponse.class);
        orderDetailResponse.setNameProduct(nameProduct);
        return orderDetailResponse;
    }

    //find by id
    public OrderDetail findById(UUID id){
        return orderDetailRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
    }

    //findByIdWithnameProduct
    public OrderDetailResponse findByIdWithNameProduct(UUID id){
        var dataExist = findById(id);
        OrderDetailResponse orderDetailResponse = modelMapper.map(dataExist, OrderDetailResponse.class);
        for (Product e : getAllProduct()) {
            orderDetailResponse.setNameProduct(e.getName());
        }
        return orderDetailResponse;
    }

    //edit order detail
    public OrderDetailResponse editData(UUID id, OrderDetailRequest request){
        Long total = 0L;
        var dataExist = findById(id);
        String nameProduct = null;
        OrderDetail saveData = null;
        boolean productFound = false;
        for (Product e : getAllProduct()) {
            if (request.getId_product().equals(e.getId())){
                total = e.getPrice() * request.getQuantity();
                dataExist.setQuantity(request.getQuantity());
                dataExist.setId_product(request.getId_product());
                dataExist.setTotalPrice(total);
                saveData = orderDetailRepository.save(dataExist);
                nameProduct = e.getName();
                productFound = true;
                break;
            }
        }
        if (!productFound){
            throw new DataNotFoundException("Product Not Available");
        }
        OrderDetailResponse orderDetailResponse = modelMapper.map(saveData, OrderDetailResponse.class);
        orderDetailResponse.setNameProduct(nameProduct);
        return orderDetailResponse;

    }
    //delete order detail
    public void deleteProduct(UUID id){
        var dataExist = findById(id);
        orderDetailRepository.delete(dataExist);
    }

    //to get order is completed
    public List<OrderCompleted> orderCompletedByProduct(UUID id_product){
        List<OrderCompleted> response = orderDetailRepository.orderCompletedByProduct(id_product);
        if (response.isEmpty()){
            return null;
        } else {
            return response;
        }

    }
}
