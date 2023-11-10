package com.example.challange5.model.dto;

import lombok.*;

import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderResponse<T> {
    private UUID id;
    private String username;
    private Long totalPrice;
    private Boolean completed;
    private T data;
}
