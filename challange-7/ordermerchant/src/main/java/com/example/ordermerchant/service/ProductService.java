package com.example.ordermerchant.service;

import com.example.ordermerchant.advice.advice.DataNotFoundException;
import com.example.ordermerchant.client.OrderCompletedClient;
import com.example.ordermerchant.dto.Merchant.MerchantRequest;
import com.example.ordermerchant.dto.Product.FullOrderByProduct;
import com.example.ordermerchant.dto.Product.ProductRequest;
import com.example.ordermerchant.dto.Product.ProductResponse;
import com.example.ordermerchant.model.Merchant;
import com.example.ordermerchant.model.Product;
import com.example.ordermerchant.repository.MerchantRepository;
import com.example.ordermerchant.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderCompletedClient orderCompletedClient;


    //findById
    public Product findById(UUID id){
        return productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
    }

    //find all product
    public List<ProductResponse> findAllProduct(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(x -> modelMapper.map(x, ProductResponse.class))
                .collect(Collectors.toList());
    }

    //add Product
    public ProductResponse addProduct(UUID id_merchant, ProductRequest request){
        Optional<Merchant> findMerchant = merchantRepository.findById(id_merchant);
        if (findMerchant.isPresent()){
            Product productCreate = Product.builder()
                    .name(request.getName())
                    .price(request.getPrice())
                    .merchant(findMerchant.get())
                    .build();
            productRepository.save(productCreate);
            ProductResponse productResponse = modelMapper.map(productCreate, ProductResponse.class);
            productResponse.setMerhant_id(findMerchant.get().getId());
            return productResponse;
        } else {
            return null;
        }
    }

    //edit product
    public Product editProduct(UUID id, ProductRequest request){
        var dataExist = findById(id);
        dataExist.setName(request.getName());
        dataExist.setPrice(request.getPrice());
        return productRepository.save(dataExist);
    }
    //delete product
    public void deleteProduct(UUID id){
        var dataExist = findById(id);
        productRepository.delete(dataExist);
    }

    //get data with order completed
    public FullOrderByProduct findAllOrderCompletedByProduct(UUID id_product){
        //menemukan product
        var prduct = productRepository.findById(id_product)
                .orElse(
                        Product.builder()
                                .name("NOT_FOUND")
                                .build()
                );
        var studdents = orderCompletedClient.orderCompletedByProduct(id_product);
        return FullOrderByProduct.builder()
                .name(prduct.getName())
                .id(prduct.getId())
                .orderCompleteds(studdents)
                .build();
    }

}
