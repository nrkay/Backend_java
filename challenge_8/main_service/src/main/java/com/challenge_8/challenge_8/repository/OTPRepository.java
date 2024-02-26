package com.challenge_8.challenge_8.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge_8.challenge_8.entity.OTP;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    List<OTP> findAllByOneTimePasswordCode(Integer otpCode);
}
