package com.example.challange5.controller;


import com.example.challange5.exception.DataNotFound;
import com.example.challange5.exception.DeleteSuccess.DeleteSuccess;
import com.example.challange5.model.Merchant;
import com.example.challange5.model.Product;
import com.example.challange5.model.Respon.ResponData;
import com.example.challange5.model.dto.MerchantResponse;
import com.example.challange5.model.dto.ProductRequest;
import com.example.challange5.model.dto.ProductResponse;
import com.example.challange5.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;



    @PostMapping
    public ResponseEntity<ResponData<ProductResponse>> create(@RequestHeader("id") UUID id, @RequestBody Product product) {
        ProductResponse response = productService.create(id, product);
        ResponData<ProductResponse> productResponseResponData = new ResponData<>();
        if (Objects.nonNull(response)) {
            productResponseResponData.setStatus("Success");
            productResponseResponData.setMessage("Add Data success");
            productResponseResponData.setData(response);
            return ResponseEntity.ok(productResponseResponData);
        } else {
            productResponseResponData.setStatus("error");
            productResponseResponData.setMessage("add data failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productResponseResponData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") UUID id){
        Product productResponse = productService.remove(id);
        if (Objects.nonNull(productResponse)){
            //memanggil deleteSuccess
            return DeleteSuccess.deleteSuccess();
        } else {
            throw new DataNotFound();
        }
    }

   @PutMapping("/{id}")
   public ResponseEntity<ResponData<ProductResponse>> update(@PathVariable("id") UUID id, @RequestBody ProductRequest productRequest){
       ProductResponse response = productService.update(id);
       ResponData<ProductResponse> bodyResponse = new ResponData<>();
       if (response != null) {
           bodyResponse.setData(response);
           bodyResponse.setStatus("Success");
           bodyResponse.setMessage("Edit Data Success");
           return ResponseEntity.ok(bodyResponse);
       } else {
           throw new DataNotFound();
       }
   }

}
