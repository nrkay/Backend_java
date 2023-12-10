package com.khairiyah.revisichallange4.repository;

import com.khairiyah.revisichallange4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}