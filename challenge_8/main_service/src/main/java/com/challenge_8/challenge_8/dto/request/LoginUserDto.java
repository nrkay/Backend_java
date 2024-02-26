package com.challenge_8.challenge_8.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
