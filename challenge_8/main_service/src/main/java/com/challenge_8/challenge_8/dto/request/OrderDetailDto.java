package com.challenge_8.challenge_8.dto.request;

import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderDetailDto {
    @NotNull
    @UUID
    private String productId;

    @NotNull
    @Positive
    private Integer quantity;
}
