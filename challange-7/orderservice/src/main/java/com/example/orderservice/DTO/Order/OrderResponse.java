package com.example.orderservice.DTO.Order;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private UUID id;
    private UUID id_user;
    private String location;
    private LocalDate orderTime;
    private Boolean completed;

}
