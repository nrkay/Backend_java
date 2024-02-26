package com.challenge_8.challenge_8.dto.request;

import java.util.Optional;
import java.util.UUID;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProductDto {
    private Optional<UUID> merchantId;

    private Optional<@Size(min = 3, message = "must has min 3 characters") String> productName;

    private Optional<Double> price;

    private Optional<Integer> stock;
}
