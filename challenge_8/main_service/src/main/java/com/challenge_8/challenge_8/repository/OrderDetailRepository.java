package com.challenge_8.challenge_8.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge_8.challenge_8.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {

}
