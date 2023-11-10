package com.example.challange5.service;


import com.example.challange5.exception.DataNotFound;
import com.example.challange5.model.Merchant;
import com.example.challange5.model.Product;
import com.example.challange5.model.dto.MerchantResponse;
import com.example.challange5.model.dto.ProductRequest;
import com.example.challange5.model.dto.ProductResponse;
import com.example.challange5.repository.MerchantRepository;
import com.example.challange5.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

//    public Product create(UUID id, Product product){
//        Optional<Merchant> merchant = merchantRepository.findById(id);
//        if (merchant.isPresent()){
//            Merchant existingMerchant = merchant.get();
//            Product productCreate = Product.builder()
//                    .name(product.getName())
//                    .price(product.getPrice())
//                    .merchant(existingMerchant)
//                    .build();
//            log.info("ini productCreate : {}", productCreate);
//            return  productRepository.save(productCreate);
//        } else {
//            return null;
//        }
//    }

    public ProductResponse create(UUID id, Product product){
        Optional<Merchant> merchant = merchantRepository.findById(id);
        if (merchant.isPresent()){
            Merchant existingMerchant = merchant.get();
            Product productCreate = Product.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .merchant(existingMerchant)
                    .build();
            log.info("ini productCreate : {}", productCreate);
            //save data
            productRepository.save(productCreate);
            //konversi dto untuk return
            ProductResponse productResponse = modelMapper.map(productCreate, ProductResponse.class);
            productResponse.setMerchant(productCreate.getMerchant().getName_merchant());
            return productResponse;
        } else {
            return null;
        }
    }
    public ProductResponse update(UUID id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            if (productOptional.get().getDeleted().equals(false)) {
                log.info("ini productUpdate : {}", productOptional);
                return modelMapper.map(productOptional.get(), ProductResponse.class);
            } else {
                throw new DataNotFound();
            }
        } else {
            log.info("Update Product is null");
            throw new DataNotFound();
        }
    }

    public Product remove(UUID id){
         Optional<Product> response = productRepository.findById(id);
         if (response.isPresent()){
             if (response.get().getDeleted().equals(false)){
                 //delete product
                 productRepository.deleteById(id);
                 log.info("delete product is success {}", response.get());
                 return response.get();
             } else {
                 log.info("error delete");
                throw new DataNotFound();
             }
         } else {
             log.info("error delete");
             throw new DataNotFound();
         }
    }
}
