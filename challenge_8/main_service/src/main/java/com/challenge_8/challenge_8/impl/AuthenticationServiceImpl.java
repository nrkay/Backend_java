package com.challenge_8.challenge_8.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge_8.challenge_8.dto.request.EnableUserDto;
import com.challenge_8.challenge_8.dto.request.ForgotPasswordDto;
import com.challenge_8.challenge_8.dto.request.LoginUserDto;
import com.challenge_8.challenge_8.dto.request.LoginUserGoogleDto;
import com.challenge_8.challenge_8.dto.request.RegisterUserDto;
import com.challenge_8.challenge_8.dto.response.AuthenticationDto;
import com.challenge_8.challenge_8.dto.response.UserDto;
import com.challenge_8.challenge_8.entity.RefreshToken;
import com.challenge_8.challenge_8.entity.User;
import com.challenge_8.challenge_8.exception.ApiException;
import com.challenge_8.challenge_8.repository.RefreshTokenRepository;
import com.challenge_8.challenge_8.repository.RoleRepository;
import com.challenge_8.challenge_8.repository.UserRepository;
import com.challenge_8.challenge_8.service.AuthenticationService;
import com.challenge_8.challenge_8.service.JWTService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public User register(RegisterUserDto request) throws ApiException {
        Optional<User> userOnDb = userRepository.findByUsername(request.getUsername());
        boolean emailAlreadyExist = userRepository.existsByEmailAddress(request.getEmailAddress());

        if (userOnDb.isPresent())
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "Username '" + request.getUsername() + "' is already exist!");

        if (emailAlreadyExist)
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "Email '" + request.getEmailAddress() + "' is already exist!");

        User user = modelMapper.map(request, User.class);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_CUSTOMER").get()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(false);
        return userRepository.save(user);
    }

    @Override
    public AuthenticationDto login(LoginUserDto request) throws ApiException {
        Optional<User> userOnDb = userRepository.findByUsername(request.getUsername());

        if (userOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "User with username '" + request.getUsername() + "' doesn't exist");

        if (!userOnDb.get().isEnabled())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "Your account hasn't activated yet");

        if (!userOnDb.get().getRegisterMethod().equals("manual"))
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "You can only sign in using OAUTH " + userOnDb.get().getRegisterMethod());

        try {
            User user = userOnDb.get();
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()));

            AuthenticationDto authDto = new AuthenticationDto();
            authDto.setUserData(modelMapper.map(user, UserDto.class));
            authDto.setAccessToken(jwtService.generateToken(user));
            authDto.setRefreshToken(generateRefreshToken(user));
            return authDto;
        } catch (AuthenticationException e) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Password doesn't match!");
        }
    }

    @Override
    public AuthenticationDto loginGoogle(LoginUserGoogleDto request) throws ApiException {
        // TODO: validate token structure, signature, and expiration

        try {
            JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
            // step 1 : set toke : google akan memvalidasi token yang kita kirim
            GoogleCredential credential = new GoogleCredential().setAccessToken(request.getGoogleAccessToken());

            // step 2 : get informasi akn dikonversi bentuk objek
            Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), JSON_FACTORY, credential)
                    .setApplicationName(
                            "Oauth2")
                    .build();

            // step 3 : oauth2 akan diolah oleh Userinfoplus : goodle (DTO)
            Userinfoplus profile = oauth2.userinfo().get().execute();
            log.info("isi profile " + profile.toPrettyString());

            // step 4 : kita hanya perlu email, full name dari DTO Userinfoplus dan kita
            // chek ke db, untuk validasi
            Optional<User> userOnDb = userRepository.findByEmailAddress(profile.getEmail());
            AuthenticationDto authDto = new AuthenticationDto();
            if (userOnDb.isEmpty()) {
                User user = new User();
                user.setUsername(profile.getGivenName() + profile.getFamilyName() + profile.getId());
                user.setEmailAddress(profile.getEmail());
                user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_CUSTOMER").get()));
                user.setPassword(passwordEncoder.encode(profile.getEmail() + profile.getId()));
                user.setEnabled(true);
                user.setRegisterMethod("google");
                User newUser = userRepository.save(user);

                authDto.setUserData(modelMapper.map(newUser, UserDto.class));
                authDto.setAccessToken(jwtService.generateToken(newUser));
                authDto.setRefreshToken(generateRefreshToken(newUser));
            } else {
                User user = userOnDb.get();
                authDto.setUserData(modelMapper.map(user, UserDto.class));
                authDto.setAccessToken(jwtService.generateToken(user));
                authDto.setRefreshToken(generateRefreshToken(user));
            }
            return authDto;
        } catch (GoogleJsonResponseException e) {
            log.error(e.getDetails().getMessage());
            throw new ApiException(HttpStatus.BAD_GATEWAY, e.getDetails().getMessage());
        } catch (Exception e) {
            log.error("haha " + e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public AuthenticationDto enableUser(EnableUserDto request) throws ApiException {
        Optional<User> userOnDb = userRepository.findByEmailAddress(request.getEmailAddress());

        if (userOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "User with username '" + request.getEmailAddress() + "' doesn't exist");

        if (userOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "User with username '" + request.getEmailAddress() + "' doesn't exist");

        if (userOnDb.get().isEnabled())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "User with username '" + request.getEmailAddress() + "' already enabled");

        User user = userOnDb.get();
        user.setEnabled(true);
        userRepository.save(user);

        AuthenticationDto authDto = new AuthenticationDto();
        authDto.setUserData(modelMapper.map(user, UserDto.class));
        authDto.setAccessToken(jwtService.generateToken(user));
        authDto.setRefreshToken(generateRefreshToken(user));
        return authDto;
    }

    @Override
    public User updateUserPassword(ForgotPasswordDto request) throws ApiException {
        Optional<User> userOnDb = userRepository.findByEmailAddress(request.getEmailAddress());

        if (userOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "User with email '" + request.getEmailAddress() + "' doesn't exist");

        User user = userOnDb.get();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return userRepository.save(user);
    }

    public String generateRefreshToken(UserDetails userDetails) throws ApiException {
        Optional<User> userOnDb = userRepository.findByUsername(userDetails.getUsername());

        if (userOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "User with username " + userDetails.getUsername() + " is not found!");

        Optional<RefreshToken> existedTokenOnDb = refreshTokenRepository.findByUser(userOnDb.get());

        if (existedTokenOnDb.isPresent())
            refreshTokenRepository.delete(existedTokenOnDb.get());

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userOnDb.get());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        return refreshTokenRepository.save(refreshToken).getToken();
    }

}
