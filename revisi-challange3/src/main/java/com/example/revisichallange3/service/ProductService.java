package com.example.revisichallange3.service;

import com.example.revisichallange3.Advice.handleException.DataNotFoundException;
import com.example.revisichallange3.dto.product.ProductRequest;
import com.example.revisichallange3.dto.product.ProductResponse;
import com.example.revisichallange3.model.Merchant;
import com.example.revisichallange3.model.Product;
import com.example.revisichallange3.repository.MerchantRepository;
import com.example.revisichallange3.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ModelMapper modelMapper;

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
            if (Objects.nonNull(productResponse)) {
                productResponse.setMerhant_id(findMerchant.get().getId());
            }
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


}
