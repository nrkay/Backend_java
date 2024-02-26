package com.challenge_8.challenge_8.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidateRefreshTokenDto {
    @NotNull
    private String refreshToken;
}
