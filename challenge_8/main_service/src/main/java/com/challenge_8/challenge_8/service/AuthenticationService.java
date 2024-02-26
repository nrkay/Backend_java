package com.challenge_8.challenge_8.service;

import com.challenge_8.challenge_8.dto.request.EnableUserDto;
import com.challenge_8.challenge_8.dto.request.ForgotPasswordDto;
import com.challenge_8.challenge_8.dto.request.LoginUserDto;
import com.challenge_8.challenge_8.dto.request.LoginUserGoogleDto;
import com.challenge_8.challenge_8.dto.request.RegisterUserDto;
import com.challenge_8.challenge_8.dto.response.AuthenticationDto;
import com.challenge_8.challenge_8.entity.User;
import com.challenge_8.challenge_8.exception.ApiException;

public interface AuthenticationService {
    public User register(RegisterUserDto request) throws ApiException;

    public AuthenticationDto login(LoginUserDto request) throws ApiException;

    public AuthenticationDto loginGoogle(LoginUserGoogleDto request) throws ApiException;

    public AuthenticationDto enableUser(EnableUserDto request) throws ApiException;

    public User updateUserPassword(ForgotPasswordDto request) throws ApiException;
}
