package com.example.challange5.service;


import com.example.challange5.model.CustomerUser;
import com.example.challange5.repository.CustomerUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CustomerUserService {
    @Autowired
    private CustomerUserRepository customerUserRepository;

    //get user by id
    public CustomerUser get(UUID id){
        Optional<CustomerUser> respon = customerUserRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                CustomerUser customerUserRespon = respon.get();
                log.info("user is found : {}", customerUserRespon);
                return customerUserRespon;
            } else {
                log.info("User was deleted");
                return null;
            }
        } else {
            log.info("User not found");
            return null;
        }
    }

    public CustomerUser save(CustomerUser customerUser){
        return customerUserRepository.save(customerUser);
    }

    public CustomerUser remove(UUID id){
        Optional<CustomerUser> respon = customerUserRepository.findById(id);
        if (respon.isPresent()) {
            if (respon.get().getDeleted().equals(false)) {
                CustomerUser customerUserRespon = respon.get();
                customerUserRepository.deleteById(id);
                log.info("Delete Success");
                return customerUserRespon;

            } else {
                log.info("data was deleted");
                return null;
            }
        } else {
            log.info("data not found");
            return null;
        }
    }

    public CustomerUser updateUser(UUID id, CustomerUser request){
        Optional<CustomerUser> getUser = customerUserRepository.findById(id);
        if (getUser.isPresent()){
            if (getUser.get().getDeleted().equals(false)){
                getUser.get().setUsername(request.getUsername());
                getUser.get().setPassword(request.getPassword());
                log.info("update user = {}", getUser);
                return customerUserRepository.save(getUser.get());
            } else {
                log.info("data was deleted");
                return null;
            }
        } else {
            log.info("data not found");
            return null;
        }
    }
}
