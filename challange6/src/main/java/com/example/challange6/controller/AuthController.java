package com.example.challange6.controller;


import com.example.challange6.dto.request.LoginRequest;
import com.example.challange6.dto.request.SignUpRequest;
import com.example.challange6.dto.response.JwtResponse;
import com.example.challange6.dto.response.MessageResponse;
import com.example.challange6.dto.response.ResponData;
import com.example.challange6.dto.response.ResponseHandler;
import com.example.challange6.model.Role;
import com.example.challange6.model.User;
import com.example.challange6.model.enums.ERole;
import com.example.challange6.repository.RoleRepository;
import com.example.challange6.repository.UserRepository;
import com.example.challange6.security.jwt.JwtUtils;
import com.example.challange6.security.service.UserDetailsImpl;
import com.example.challange6.service.SignUpService;
import com.example.challange6.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private SignUpService signUpService;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), roles);
//        return ResponseHandler.generateResponse("success", jwtResponse, null, HttpStatus.OK);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


        log.info("tes uda masuk eksini blm");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), roles);
        return ResponseHandler.generateResponse("success", jwtResponse, null, HttpStatus.OK);

    }


//    @PostMapping("/signup")
//    public String authenticateUser(){
//        return "cek";
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignUpRequest signUpRequest){
        if (signUpService.checkGmail(signUpRequest)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        if (signUpService.checkUsername(signUpRequest)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }


        User response = signUpService.createAccount(signUpRequest);
        if (Objects.nonNull(response)){
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error"));
        }
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignUpRequest signUpRequest){
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_BUYER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "seller":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_SELLER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_BUYER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//        user.setRoles(roles);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }



}

