package com.example.challange5.model.dto.OrderDto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderResponse {
    private UUID id;
    private String username;
    private UUID idOrder;
    private Long totalPrice;
}
