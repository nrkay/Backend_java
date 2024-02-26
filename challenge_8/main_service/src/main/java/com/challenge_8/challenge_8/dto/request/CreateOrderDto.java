package com.challenge_8.challenge_8.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderDto {
    @NotEmpty
    private String destinationAddress;

    @NotNull
    private UUID userId;

    @NotNull
    private List<OrderDetailDto> orderDetails;
}
