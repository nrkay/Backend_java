package com.example.demo.repository;

import com.example.demo.model.DTO.ProductDTO;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    //Query for Get Open Product Merchant by Join Query
    @Query("SELECT p FROM Product p JOIN p.merchant m WHERE m.open = true")
    List<Product> findOpenProductMerchant();

    //Query For Delete
    @Modifying
    @Query("DELETE FROM Product p WHERE p.name = :productName")
    void deleteProduct(@Param("productName") String productName);
}

    //@Modifying
    //@Query("UPDATE FROM Product p SET p.name =:nameProduct, p.price=:priceProduct, WHERE p.id =idProduct")
    //Product updateProduct(@Param("nameProduct") Product.)

