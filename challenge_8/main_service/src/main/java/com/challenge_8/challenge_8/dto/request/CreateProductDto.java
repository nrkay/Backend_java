package com.challenge_8.challenge_8.dto.request;

import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductDto {
    @NotNull
    @UUID
    private String merchantId;

    @NotBlank
    @Size(min = 3, message = "must has min 3 characters")
    private String productName;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Positive
    private Integer stock;
}
