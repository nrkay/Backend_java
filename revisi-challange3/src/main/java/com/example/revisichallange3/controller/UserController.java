package com.example.revisichallange3.controller;

import com.example.revisichallange3.dto.merchant.MerchantRequest;
import com.example.revisichallange3.dto.merchant.MerchantResponse;
import com.example.revisichallange3.dto.responseHandler.ResponHandler;
import com.example.revisichallange3.dto.user.UserRequest;
import com.example.revisichallange3.dto.user.UserResponse;
import com.example.revisichallange3.service.MerchantService;
import com.example.revisichallange3.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper modelMapper;
    //add
    @PostMapping("/")
    public ResponseEntity<Object> createData (@RequestBody UserRequest request){
        Optional<UserResponse> response = Optional.ofNullable(service.save(request));
        if (response.isPresent()){
            return ResponHandler.responsePost("Successfully Add Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") UUID id, @RequestBody UserRequest request){
        Optional<UserResponse> response = Optional.ofNullable(service.update(id, request));
        if(response.isPresent()){
            return ResponHandler.responsePost("Successfully Edit Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable("id") UUID id){
        service.delete(id);
        return ResponHandler.responsePost("Successfully Delete Data", HttpStatus.OK, "delete data id :");
    }
}
