package com.example.challange5.service;


import com.example.challange5.model.Merchant;
import com.example.challange5.model.Product;
import com.example.challange5.model.dto.ProductRequest;
import com.example.challange5.repository.MerchantRepository;
import com.example.challange5.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public Product create(UUID id, Product product){
        Optional<Merchant> merchant = merchantRepository.findById(id);
        if (merchant.isPresent()){
            Merchant existingMerchant = merchant.get();
            Product productCreate = Product.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .merchant(existingMerchant)
                    .build();
            log.info("ini productCreate : {}", productCreate);
            return  productRepository.save(productCreate);
        } else {
            return null;
        }
    }
    public Product update(UUID id, ProductRequest productRequest){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            productOptional.get().setName(productRequest.getProductName());
            productOptional.get().setPrice(productRequest.getProductPrice());

            log.info("ini productUpdate : {}", productOptional);
            return  productRepository.save(productOptional.get());
        } else {
            log.info("Update Product is null");
            return null;
        }
    }

    public void remove(UUID id){
         productRepository.deleteById(id);
    }
}
