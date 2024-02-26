package com.challenge_8.challenge_8.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordSendOtpDto {
    @NotBlank
    @Email
    private String emailAddress;
}
