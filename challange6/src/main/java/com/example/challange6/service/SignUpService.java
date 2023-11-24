package com.example.challange6.service;

import com.example.challange6.dto.request.SignUpRequest;
import com.example.challange6.model.Role;
import com.example.challange6.model.User;
import com.example.challange6.model.enums.ERole;
import com.example.challange6.repository.RoleRepository;
import com.example.challange6.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class SignUpService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    //cek gmail ready or not
    public boolean checkUsername(SignUpRequest request){
        return userRepository.existsByUsername(request.getUsername());
    }

    public boolean checkGmail(SignUpRequest request){
        return userRepository.existsByEmail(request.getEmail());
    }

    public User createAccount(@NotNull SignUpRequest signUpRequest){
        log.info("ini role nya {}", signUpRequest.getRole());
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_BUYER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "seller":
                        Role modRole = roleRepository.findByName(ERole.ROLE_SELLER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_BUYER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    //cek username ready or not
    //create account
}
