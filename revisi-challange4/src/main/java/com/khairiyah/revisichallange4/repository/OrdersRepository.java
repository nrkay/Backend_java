package com.khairiyah.revisichallange4.repository;

import com.khairiyah.revisichallange4.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}