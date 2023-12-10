package com.khairiyah.revisichallange4.service;

import com.khairiyah.revisichallange4.advance.handleException.DataNotFoundException;
import com.khairiyah.revisichallange4.dto.user.UserRequest;
import com.khairiyah.revisichallange4.dto.user.UserResponse;
import com.khairiyah.revisichallange4.model.Merchant;
import com.khairiyah.revisichallange4.model.User;
import com.khairiyah.revisichallange4.repository.MerchantRepository;
import com.khairiyah.revisichallange4.repository.UsersRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public User addUser(String username, String password) {
        return usersRepository.addUser(username, password);
    }

    @Transactional
    public User editUser(String username, String password, UUID id) {
        return usersRepository.editUser(id, username, password);
    }

    @Transactional
    public void deleteUser(UUID id){
        usersRepository.deleteUserById(id);
    }

    // find by id
//    public User findById(UUID id){
//        return usersRepository.findById(id).orElseThrow(() ->
//                new DataNotFoundException("Data is Not Found"));
//    }

    // add data
//    public UserResponse save(UserRequest request){
//        User req =  usersRepository.save(modelMapper.map(request, User.class));
//        return modelMapper.map(req, UserResponse.class);
//    }

    // delete data
//    public void delete(UUID id){
//        Optional<User> user = usersRepository.findById(id);
//        if (Objects.nonNull(user)){
//            User response = user.get();
//            usersRepository.delete(response);
//        }
//    }

    // update data
//    public UserResponse update(UUID id, UserRequest request){
//        Optional<User> user = usersRepository.findById(id);
//        if (Objects.nonNull(user)){
//            User userResponse = user.get();
//            userResponse.setUsername(request.getUsername());
//            userResponse.setPassword(request.getPassword());
//            User userUpdate = usersRepository.save(userResponse);
//            return modelMapper.map(userUpdate, UserResponse.class);
//        } else {
//            return null;
//        }
//    }
}
