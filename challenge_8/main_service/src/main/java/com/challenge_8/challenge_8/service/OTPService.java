package com.challenge_8.challenge_8.service;

import com.challenge_8.challenge_8.entity.OTP;
import com.challenge_8.challenge_8.exception.ApiException;

public interface OTPService {
    public OTP generateOTP();

    public void otpIsValid(Integer otpCode) throws ApiException;
}
