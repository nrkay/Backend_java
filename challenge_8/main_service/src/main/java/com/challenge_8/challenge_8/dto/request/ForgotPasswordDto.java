package com.challenge_8.challenge_8.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ForgotPasswordDto {
    @NotBlank
    @Email
    private String emailAddress;

    @NotNull
    private Integer otpCode;

    @NotNull
    private String newPassword;
}
