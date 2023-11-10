package com.example.challange5.repository;

import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.dto.OrderDto.UserOrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, UUID> {
    List<CustomerOrder> findByOrderTimeBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT NEW com.example.challange5.model.dto.OrderDto.UserOrderResponse(u.id, u.username, o.id, " +
            "o.totalPrice ) " +
            "FROM CustomerOrder o INNER JOIN o.user u WHERE u.id= :idUsername")
    List<UserOrderResponse> findAllOrdersbyUserId(UUID idUsername );
}
