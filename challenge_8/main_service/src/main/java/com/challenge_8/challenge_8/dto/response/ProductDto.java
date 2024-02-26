package com.challenge_8.challenge_8.dto.response;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDto {
    private UUID id;

    @NotNull
    private MerchantDto merchant;

    @NotEmpty
    private String productName;

    @NotNull
    private Double price;

    @NotNull
    @Positive
    private Integer stock;
}
