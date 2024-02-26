package com.example.demo.service;

import com.example.demo.model.CustomerUser;

import java.util.UUID;

public interface UserService {

    CustomerUser create(CustomerUser customerUser);
    void deleteUser(String username);
    CustomerUser findUserById(UUID userId);
}
