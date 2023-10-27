package com.example.demo.service;


import com.example.demo.model.CustomerUser;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService{
    //constructor injection
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public CustomerUser create(CustomerUser customerUser) {
        return userRepository.save(customerUser);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

    @Override
    public CustomerUser findUserById(UUID userId) {
        Optional<CustomerUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new EntityNotFoundException("User not Found");
        }
    }
}
