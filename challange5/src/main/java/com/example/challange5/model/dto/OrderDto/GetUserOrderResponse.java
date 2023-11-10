package com.example.challange5.model.dto.OrderDto;

import com.example.challange5.model.dto.OrderDetailResponse;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserOrderResponse<T> {

    private UUID id;
    private String username;
    private UUID idOrder;
    private Long totalPrice;
    private T data;
}
