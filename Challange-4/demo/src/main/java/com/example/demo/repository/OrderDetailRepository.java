package com.example.demo.repository;


import com.example.demo.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
//    @Query("SELECT od FROM OrderDetail od WHERE od.customerUser.id = :orderId")
//    List<OrderDetail> findByOrderId(@Param("orderId") UUID orderId);
//
//    @Query("SELECT SUM(od.totalPrice) FROM OrderDetail od WHERE od.customerUser.id = :orderId")
//    Long calculateTotalPriceByOrderId(@Param("orderId") UUID orderId);


}
