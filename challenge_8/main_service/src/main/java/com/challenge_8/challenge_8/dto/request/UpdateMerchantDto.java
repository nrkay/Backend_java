package com.challenge_8.challenge_8.dto.request;

import java.util.Optional;

import lombok.Data;

@Data
public class UpdateMerchantDto {
    private Optional<String> merchantName;

    private Optional<String> merchantLocation;

    private Optional<Boolean> isOpen;
}
