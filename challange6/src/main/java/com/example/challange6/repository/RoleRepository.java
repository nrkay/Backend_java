package com.example.challange6.repository;

import com.example.challange6.model.Role;
import com.example.challange6.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);
}
