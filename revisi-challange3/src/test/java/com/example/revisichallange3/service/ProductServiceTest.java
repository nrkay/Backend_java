package com.example.revisichallange3.service;

import com.example.revisichallange3.Advice.handleException.DataNotFoundException;
import com.example.revisichallange3.dto.product.ProductRequest;
import com.example.revisichallange3.dto.product.ProductResponse;
import com.example.revisichallange3.model.Merchant;
import com.example.revisichallange3.model.Product;
import com.example.revisichallange3.repository.MerchantRepository;
import com.example.revisichallange3.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@SpringBootTest

public class ProductServiceTest {
    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void findById_productFound() {
        UUID productId = UUID.randomUUID();
        Product mockProduct = new Product();
        mockProduct.setId(productId);

        given(productRepository.findById(productId)).willReturn(Optional.of(mockProduct));

        Product result = productService.findById(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
    }

    @Test
    void findById_productNotFound() {
        UUID nonExistentProductId = UUID.randomUUID();

        given(productRepository.findById(nonExistentProductId)).willReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> productService.findById(nonExistentProductId));
    }

    @Test
    void findAllProduct() {
        List<Product> mockProducts = List.of(new Product(), new Product());

        given(productRepository.findAll()).willReturn(mockProducts);

        List<ProductResponse> result = productService.findAllProduct();

        assertNotNull(result);
        assertEquals(mockProducts.size(), result.size());
    }

    @Test
    void addProduct_merchantNotFound() {
        UUID nonExistentMerchantId = UUID.randomUUID();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Product Name");
        productRequest.setPrice(3l);

        given(merchantRepository.findById(nonExistentMerchantId)).willReturn(Optional.empty());

        ProductResponse result = productService.addProduct(nonExistentMerchantId, productRequest);

        assertNull(result);
    }

    @Test
    void editProduct() {
        UUID productId = UUID.randomUUID();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Updated Product");
        productRequest.setPrice(3l);

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Original Product");
        existingProduct.setPrice(3l);

        given(productRepository.findById(productId)).willReturn(Optional.of(existingProduct));
        given(productRepository.save(any(Product.class))).willReturn(existingProduct);

        Product result = productService.editProduct(productId, productRequest);

        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        assertEquals(3l, result.getPrice());
    }

    @Test
    void deleteProduct() {
        UUID productId = UUID.randomUUID();
        Product existingProduct = new Product();
        existingProduct.setId(productId);

        given(productRepository.findById(productId)).willReturn(Optional.of(existingProduct));

        productService.deleteProduct(productId);

        verify(productRepository).delete(existingProduct);
    }
}
