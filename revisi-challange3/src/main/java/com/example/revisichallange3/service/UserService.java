package com.example.revisichallange3.service;

import com.example.revisichallange3.Advice.handleException.DataNotFoundException;
import com.example.revisichallange3.dto.merchant.MerchantRequest;
import com.example.revisichallange3.dto.merchant.MerchantResponse;
import com.example.revisichallange3.dto.user.UserRequest;
import com.example.revisichallange3.dto.user.UserResponse;
import com.example.revisichallange3.model.Merchant;
import com.example.revisichallange3.model.User;
import com.example.revisichallange3.repository.MerchantRepository;
import com.example.revisichallange3.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;
    // find by id
    public User findById(UUID id){
        return usersRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Data is Not Found"));
    }

    // add data
    public UserResponse save(UserRequest request){
        User req =  usersRepository.save(modelMapper.map(request, User.class));
        return modelMapper.map(req, UserResponse.class);
    }

    // delete data
    public void delete(UUID id){
        Optional<User> user = usersRepository.findById(id);
        if (Objects.nonNull(user)){
            User response = user.get();
            usersRepository.delete(response);
        }
    }

    // update data
    public UserResponse update(UUID id, UserRequest request){
        Optional<User> user = usersRepository.findById(id);
        if (Objects.nonNull(user)){
            User userResponse = user.get();
            userResponse.setUsername(request.getUsername());
            userResponse.setPassword(request.getPassword());
            User userUpdate = usersRepository.save(userResponse);
            return modelMapper.map(userUpdate, UserResponse.class);
        } else {
            return null;
        }
    }
}
