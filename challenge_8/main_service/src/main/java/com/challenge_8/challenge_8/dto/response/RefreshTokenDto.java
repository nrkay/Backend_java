package com.challenge_8.challenge_8.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshTokenDto {
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
}
