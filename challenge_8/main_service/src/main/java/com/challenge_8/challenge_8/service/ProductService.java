package com.challenge_8.challenge_8.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.challenge_8.challenge_8.dto.request.CreateProductDto;
import com.challenge_8.challenge_8.dto.request.UpdateProductDto;
import com.challenge_8.challenge_8.dto.response.ProductDto;
import com.challenge_8.challenge_8.entity.Product;
import com.challenge_8.challenge_8.exception.ApiException;

public interface ProductService {
    public ProductDto createProduct(CreateProductDto request) throws ApiException;

    public ProductDto updateProduct(UUID productId, UpdateProductDto request,
            @AuthenticationPrincipal UserDetails userDetails) throws ApiException;

    public ProductDto getProductById(UUID productId) throws ApiException;

    public ProductDto deleteProduct(UUID productId) throws ApiException;;

    public Page<ProductDto> getAllProducts(Specification<Product> filterQueries, Pageable paginationQueries);

}
