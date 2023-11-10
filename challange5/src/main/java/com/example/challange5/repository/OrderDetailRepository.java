package com.example.challange5.repository;

import com.example.challange5.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findByCustomerOrder_Id(UUID orderId);

}
