package com.example.challange5.controller;


import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.CustomerUser;
import com.example.challange5.service.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class CustomerUserController {
    @Autowired
    private CustomerUserService customerUserService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerUser> getById(@PathVariable("id") UUID id){
        CustomerUser respon = customerUserService.get(id);
        if (respon != null){
            return ResponseEntity.ok(respon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public CustomerUser create(@RequestBody CustomerUser customerUser){
        return customerUserService.save(customerUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        CustomerUser response = customerUserService.remove(id);
        if (response != null) {
            return ResponseEntity.ok("Delete Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerUser> update(@PathVariable("id") UUID id, @RequestBody CustomerUser customerUser){
        CustomerUser respon = customerUserService.updateUser(id, customerUser);
        if (respon != null){
            return ResponseEntity.ok(respon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
