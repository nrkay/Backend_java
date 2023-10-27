package com.example.demo.repository;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, UUID> {
    @Query("SELECT p FROM Product p WHERE p.name = :nameProduct")
    Product findProductByName(@Param("nameProduct") String nameProduct);

}
