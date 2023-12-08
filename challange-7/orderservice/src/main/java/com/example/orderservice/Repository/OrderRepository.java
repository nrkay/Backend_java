package com.example.orderservice.Repository;

import com.example.orderservice.Model.Order;
import com.example.orderservice.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
