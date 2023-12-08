package com.example.orderservice.DTO.OrderDetail;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private UUID id;
    private UUID id_product;
    private String nameProduct;
    private int quantity;
    private Long totalPrice;
}
