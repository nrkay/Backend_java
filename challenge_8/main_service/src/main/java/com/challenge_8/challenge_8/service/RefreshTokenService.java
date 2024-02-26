package com.challenge_8.challenge_8.service;

import com.challenge_8.challenge_8.dto.response.RefreshTokenDto;
import com.challenge_8.challenge_8.exception.ApiException;

public interface RefreshTokenService {
    public RefreshTokenDto generateToken(String token) throws ApiException;
}
