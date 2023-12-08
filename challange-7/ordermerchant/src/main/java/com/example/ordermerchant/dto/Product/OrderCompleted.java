package com.example.ordermerchant.dto.Product;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompleted {
    private UUID id;
    private UUID id_product;
    private UUID id_user;
    private LocalDate complated_at;
    private int quantity;
    private Long totalPrice;
}
