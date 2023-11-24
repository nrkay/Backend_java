package com.example.challange6.service;

import com.example.challange6.model.User;
import com.example.challange6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User save(User request){
        User user = userRepository.save(request);
        return user;
    }


}
