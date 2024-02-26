package com.challenge_8.challenge_8.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge_8.challenge_8.dto.request.EnableUserDto;
import com.challenge_8.challenge_8.dto.request.ForgotPasswordDto;
import com.challenge_8.challenge_8.dto.request.ForgotPasswordSendOtpDto;
import com.challenge_8.challenge_8.dto.request.LoginUserDto;
import com.challenge_8.challenge_8.dto.request.LoginUserGoogleDto;
import com.challenge_8.challenge_8.dto.request.RegisterUserDto;
import com.challenge_8.challenge_8.dto.request.ValidateRefreshTokenDto;
import com.challenge_8.challenge_8.dto.response.AuthenticationDto;
import com.challenge_8.challenge_8.dto.response.RefreshTokenDto;
import com.challenge_8.challenge_8.entity.OTP;
import com.challenge_8.challenge_8.entity.User;
import com.challenge_8.challenge_8.exception.ApiException;
import com.challenge_8.challenge_8.service.AuthenticationService;
import com.challenge_8.challenge_8.service.OTPService;
import com.challenge_8.challenge_8.service.RefreshTokenService;
import com.challenge_8.challenge_8.utils.EmailSender;
import com.challenge_8.challenge_8.utils.EmailTemplate;
import com.challenge_8.challenge_8.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    OTPService otpService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    EmailSender emailSender;

    @Autowired
    EmailTemplate emailTemplate;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterUserDto request) {
        try {
            User userData = authenticationService.register(request);

            OTP otp = otpService.generateOTP();

            String template = emailTemplate.getRegisterTemplate(userData.getUsername(),
                    otp.getOneTimePasswordCode());

            emailSender.sendEmail(userData.getEmailAddress(), "Register new account", template);

            Map<String, String> res = new HashMap<String, String>();
            res.put("message", "Please check your email and enter the OTP!");

            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Register Success", res);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginUserDto request) {
        try {
            AuthenticationDto userData = authenticationService.login(request);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Login Success", userData);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/login-google")
    public ResponseEntity<Object> loginGoogle(@Valid @RequestBody LoginUserGoogleDto request) {
        try {
            AuthenticationDto userData = authenticationService.loginGoogle(request);
            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Login Google Success", userData);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/enable-user")
    public ResponseEntity<Object> enableUser(@Valid @RequestBody EnableUserDto request) {
        try {
            otpService.otpIsValid(request.getOtpCode());

            AuthenticationDto userData = authenticationService.enableUser(request);

            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Register Success", userData);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/forgot-password/send-otp")
    public ResponseEntity<Object> forgotPasswordSendOtp(@Valid @RequestBody ForgotPasswordSendOtpDto request) {
        try {
            OTP otp = otpService.generateOTP();

            String template = emailTemplate.getResetPassword(request.getEmailAddress(),
                    otp.getOneTimePasswordCode());

            emailSender.sendEmail(request.getEmailAddress(), "Forgot password", template);

            Map<String, String> res = new HashMap<String, String>();
            res.put("message", "Please check your email and enter the OTP!");

            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Forgot password", res);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@Valid @RequestBody ForgotPasswordDto request) {
        try {
            otpService.otpIsValid(request.getOtpCode());
            authenticationService.updateUserPassword(request);

            Map<String, String> res = new HashMap<String, String>();
            res.put("message", "Please login again!");

            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Register Success", res);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(@Valid @RequestBody ValidateRefreshTokenDto request) {
        try {
            RefreshTokenDto accessToken = refreshTokenService.generateToken(request.getRefreshToken());

            return ResponseHandler.generateResponseSuccess(HttpStatus.OK,
                    "Refresh token Success", accessToken);
        } catch (ApiException e) {
            return ResponseHandler.generateResponseFailed(
                    e.getStatus(), e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
