package com.example.orderservice.Repository;

import com.example.orderservice.DTO.Order.OrderCompleted;
import com.example.orderservice.Model.Order;
import com.example.orderservice.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    //to get order is completed
    @Query(value = "SELECT NEW com.example.orderservice.DTO.Order.OrderCompleted(o.id, " +
            "d.id_product, o.id_user, o.complated_at, d.quantity, d.totalPrice) " +
            "FROM Order o " +
            "INNER JOIN OrderDetail d ON o.id = d.order.id " +  // Menambahkan kondisi JOIN yang sesuai
            "WHERE o.completed = true AND d.id_product = :id_product")
    List<OrderCompleted> orderCompletedByProduct(UUID id_product);

    //find OrderDetail by Order
    List<OrderDetail> findByOrder(Order order);

}
