package com.khairiyah.revisichallange4.controller;

import com.khairiyah.revisichallange4.dto.responhandler.ResponHandler;
import com.khairiyah.revisichallange4.dto.user.UserRequest;
import com.khairiyah.revisichallange4.dto.user.UserResponse;
import com.khairiyah.revisichallange4.model.User;
import com.khairiyah.revisichallange4.service.UserService;
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
        Optional<User> response = Optional.ofNullable(service.addUser(request.getUsername(), request.getPassword()));
        if (response.isPresent()){
            return ResponHandler.responsePost("Successfully Add Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") UUID id, @RequestBody UserRequest request){
        Optional<User> response = Optional.ofNullable(service.editUser(request.getUsername(), request.getPassword(), id));
        if(response.isPresent()){
            return ResponHandler.responsePost("Successfully Edit Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable("id") UUID id){
        service.deleteUser(id);
        return ResponHandler.responsePost("Successfully Delete Data", HttpStatus.OK, "delete data id :");
    }
}
