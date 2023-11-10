package com.example.challange5.model.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponse {
    private UUID id;
    private String productName;
    private Integer quantity;
    private Long totalPrice;
}
