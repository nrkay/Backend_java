package com.challenge_8.challenge_8.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotBlank
    @Size(min = 4, message = "must has min 4 characters")
    private String username;

    @NotBlank
    @Email
    private String emailAddress;

    @NotBlank
    @Size(min = 4, message = "must has min 4 characters")
    private String password;

}
