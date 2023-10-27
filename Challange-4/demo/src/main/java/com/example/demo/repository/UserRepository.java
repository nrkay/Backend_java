package com.example.demo.repository;


import com.example.demo.model.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<CustomerUser, UUID> {

    @Procedure("deleteUser")
    void deleteUser(@Param("user_name") String username);
}
