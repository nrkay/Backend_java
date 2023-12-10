package com.khairiyah.revisichallange4.repository;

import com.khairiyah.revisichallange4.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findByCustomerOrder_Id(UUID orderId);
}