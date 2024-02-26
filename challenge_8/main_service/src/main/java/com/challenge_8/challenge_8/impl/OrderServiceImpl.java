package com.challenge_8.challenge_8.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge_8.challenge_8.dto.request.CreateOrderDto;
import com.challenge_8.challenge_8.dto.request.OrderDetailDto;
import com.challenge_8.challenge_8.dto.response.OrderDto;
import com.challenge_8.challenge_8.entity.Order;
import com.challenge_8.challenge_8.entity.OrderDetail;
import com.challenge_8.challenge_8.entity.Product;
import com.challenge_8.challenge_8.entity.User;
import com.challenge_8.challenge_8.exception.ApiException;
import com.challenge_8.challenge_8.repository.OrderDetailRepository;
import com.challenge_8.challenge_8.repository.OrderRepository;
import com.challenge_8.challenge_8.repository.ProductRepository;
import com.challenge_8.challenge_8.repository.UserRepository;
import com.challenge_8.challenge_8.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Transactional(rollbackFor = { ApiException.class, Exception.class })
    @Override
    public OrderDto createOrder(CreateOrderDto request) throws ApiException {
        Optional<User> userOnDb = userRepository.findById(request.getUserId());

        if (userOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "User with id " + request.getUserId() + " is not found!");

        Order order = new Order();
        order.setDestinationAddress(request.getDestinationAddress());
        order.setUser(userOnDb.get());
        order.setOrderTime(new Date());
        Order savedOrder = orderRepository.save(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        Double finalPrice = 0.0;
        for (OrderDetailDto od : request.getOrderDetails()) {
            Optional<Product> product = productRepository.findById(UUID.fromString(od.getProductId()));
            if (product.isEmpty())
                throw new ApiException(HttpStatus.NOT_FOUND,
                        "Product with id '" + od.getProductId() + "' is not found.");

            if (od.getQuantity() > product.get().getStock())
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Product with id '" + product.get().getId() + "'' doesn't have enough stock!");

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(savedOrder);
            orderDetail.setProduct(product.get());
            orderDetail.setQuantity(od.getQuantity());
            orderDetail.setTotalPrice(product.get().getPrice() * od.getQuantity());
            orderDetails.add(orderDetail);

            product.get().setStock(product.get().getStock() - od.getQuantity());
            productRepository.save(product.get());

            finalPrice += product.get().getPrice() * od.getQuantity();
        }
        savedOrder.setOrderDetails(orderDetailRepository.saveAll(orderDetails));

        OrderDto orderDto = modelMapper.map(savedOrder, OrderDto.class);
        orderDto.setFinalPrice(finalPrice);
        return orderDto;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> ordersDto = orderRepository.findAll().stream().map(o -> {
            OrderDto orderDto = modelMapper.map(o, OrderDto.class);
            Double finalPrice = o.getOrderDetails().stream().mapToDouble(od -> od.getTotalPrice()).sum();
            orderDto.setFinalPrice(finalPrice);

            return orderDto;
        }).collect(Collectors.toList());

        return ordersDto;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(UUID userId, UserDetails userDetails) throws ApiException {
        Optional<User> userOnDb = userRepository.findById(userId);
        if (userOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "User with id " + userId + " is not found!");

        if (!userOnDb.get().getUsername().equals(userDetails.getUsername()))
            throw new ApiException(HttpStatus.FORBIDDEN, "You can't retrieve other user's order");

        List<Order> orders = orderRepository.findAllByUserId(userId);
        List<OrderDto> ordersDto = orders.stream().map(o -> {
            OrderDto orderDto = modelMapper.map(o, OrderDto.class);
            Double finalPrice = o.getOrderDetails().stream().mapToDouble(od -> od.getTotalPrice()).sum();
            orderDto.setFinalPrice(finalPrice);

            return orderDto;
        }).collect(Collectors.toList());

        return ordersDto;
    }

    @Override
    public OrderDto getOrderById(UUID orderId, UserDetails userDetails) throws ApiException {
        Optional<Order> orderOnDb = orderRepository.findById(orderId);
        if (orderOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Order with id " + orderId + " is not found!");

        if (!orderOnDb.get().getUser().getUsername().equals(userDetails.getUsername()))
            throw new ApiException(HttpStatus.FORBIDDEN, "You can't retrieve other user's order");

        OrderDto orderDto = modelMapper.map(orderOnDb.get(), OrderDto.class);
        Double finalPrice = orderOnDb.get().getOrderDetails().stream().mapToDouble(od -> od.getTotalPrice()).sum();
        orderDto.setFinalPrice(finalPrice);

        return orderDto;
    }

    @Override
    public OrderDto deleteOrder(UUID orderId) throws ApiException {
        Optional<Order> orderOnDb = orderRepository.findById(orderId);
        if (orderOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Order with id " + orderId + " is not found!");

        Order deletedOrder = orderOnDb.get();
        orderRepository.delete(deletedOrder);

        OrderDto orderDto = modelMapper.map(deletedOrder, OrderDto.class);
        Double finalPrice = orderOnDb.get().getOrderDetails().stream().mapToDouble(od -> od.getTotalPrice()).sum();
        orderDto.setFinalPrice(finalPrice);

        return orderDto;
    }

}
