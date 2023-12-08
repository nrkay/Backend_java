package com.example.orderservice.DTO.OrderDetail;

import com.example.orderservice.Model.Order;
import lombok.*;

import java.util.UUID;

@Data
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private UUID id_product;
    private int quantity;
    private Long totalPrice;
}
