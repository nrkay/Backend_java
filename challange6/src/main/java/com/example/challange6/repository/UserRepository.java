package com.example.challange6.repository;
import com.example.challange6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String Username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
