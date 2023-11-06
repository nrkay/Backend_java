package com.example.challange5.repository;

import com.example.challange5.model.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerUserRepository extends JpaRepository<CustomerUser, UUID> {
}
