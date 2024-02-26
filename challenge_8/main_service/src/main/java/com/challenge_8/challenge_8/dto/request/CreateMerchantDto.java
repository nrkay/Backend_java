package com.challenge_8.challenge_8.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateMerchantDto {
    @NotBlank()
    private String merchantName;

    @NotBlank()
    private String merchantLocation;

    private Boolean isOpen = false;
}
