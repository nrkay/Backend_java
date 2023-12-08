package com.example.ordermerchant.controller;

import com.example.ordermerchant.dto.Merchant.MerchantRequest;
import com.example.ordermerchant.dto.Merchant.MerchantResponse;
import com.example.ordermerchant.dto.ResponHandler;
import com.example.ordermerchant.model.Merchant;
import com.example.ordermerchant.service.MerchantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    @Autowired
    private MerchantService service;

    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/message")
    public String message(){
        return "test from merchat service";
    }


    // add data
    @PostMapping("/")
    public ResponseEntity<Object> createData (@RequestBody MerchantRequest request){
        Optional<MerchantResponse> response = Optional.ofNullable(service.save(request));
        if (response.isPresent()){
            return ResponHandler.responsePost("Successfully Add Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // update data
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") UUID id, @RequestBody MerchantRequest merchantRequest){
        Optional<MerchantResponse> response = Optional.ofNullable(service.update(id, merchantRequest));
        if(response.isPresent()){
            return ResponHandler.responsePost("Successfully Edit Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // delete data
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable("id") UUID id){
        service.delete(id);
        return ResponHandler.responsePost("Successfully Delete Data", HttpStatus.OK, "delete data id :");
    }

    //get all data
    @GetMapping("/")
    public ResponseEntity<Object> getAllData(){
        List<MerchantResponse> response = service.getAll();
        return ResponHandler.responsePost("Get Data is Success", HttpStatus.OK, response);
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Merchant response = service.findById(id);
        return ResponHandler.responsePost("Data is Found",
                HttpStatus.OK,
                modelMapper.map(response, MerchantResponse.class));
    }


}
