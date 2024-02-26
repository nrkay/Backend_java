package com.challenge_8.challenge_8.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.challenge_8.challenge_8.dto.request.CreateOrderDto;
import com.challenge_8.challenge_8.dto.response.OrderDto;
import com.challenge_8.challenge_8.exception.ApiException;

public interface OrderService {
    public OrderDto createOrder(CreateOrderDto request) throws ApiException;

    public List<OrderDto> getAllOrders();

    public List<OrderDto> getOrdersByUserId(UUID userId, UserDetails userDetails) throws ApiException;

    public OrderDto getOrderById(UUID orderId, UserDetails userDetails) throws ApiException;

    public OrderDto deleteOrder(UUID orderId) throws ApiException;
}
