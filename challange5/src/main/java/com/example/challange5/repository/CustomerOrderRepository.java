package com.example.challange5.repository;

import com.example.challange5.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, UUID> {
}
