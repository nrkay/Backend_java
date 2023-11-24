package com.example.challange6.controller;

import com.example.challange6.dto.response.ResponData;
import com.example.challange6.model.User;
import com.example.challange6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/public")
    public ResponseEntity<ResponData<User>> addUser(@RequestBody User u){
        User user = userService.save(u);
        ResponData<User> response = new ResponData<>();
        if (Objects.nonNull(user)){
            response.setStatus("add data success");
            response.setData(user);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus("error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
