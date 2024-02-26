package com.challenge_8.challenge_8.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.challenge_8.challenge_8.dto.response.RefreshTokenDto;
import com.challenge_8.challenge_8.entity.RefreshToken;
import com.challenge_8.challenge_8.entity.User;
import com.challenge_8.challenge_8.exception.ApiException;
import com.challenge_8.challenge_8.repository.RefreshTokenRepository;
import com.challenge_8.challenge_8.repository.UserRepository;
import com.challenge_8.challenge_8.service.JWTService;
import com.challenge_8.challenge_8.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Override
    public RefreshTokenDto generateToken(String token) throws ApiException {
        Optional<RefreshToken> tokenOnDb = refreshTokenRepository.findByToken(token);

        if (tokenOnDb.isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "Refresh token is not found");

        RefreshToken refreshToken = tokenOnDb.get();
        if (refreshToken.getExpires().before(new Date())) {
            refreshTokenRepository.delete(refreshToken);
            throw new ApiException(HttpStatus.FORBIDDEN, "Refresh token is expired. Please make a new login..!");
        }

        User user = userRepository.findByUsername(refreshToken.getUser().getUsername()).get();
        Optional<RefreshToken> existedTokenOnDb = refreshTokenRepository.findByUser(user);

        if (existedTokenOnDb.isPresent())
            refreshTokenRepository.delete(existedTokenOnDb.get());

        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setUser(user);
        newRefreshToken.setToken(UUID.randomUUID().toString());
        newRefreshToken.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setAccessToken(jwtService.generateToken(refreshToken.getUser()));
        refreshTokenDto.setRefreshToken(refreshTokenRepository.save(newRefreshToken).getToken());
        return refreshTokenDto;
    }
}
