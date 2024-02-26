package com.challenge_8.challenge_8.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    private String accessToken;

    private String refreshToken;

    private UserDto userData;
}
