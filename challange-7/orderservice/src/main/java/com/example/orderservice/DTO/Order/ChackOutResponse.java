package com.example.orderservice.DTO.Order;

import com.example.orderservice.DTO.OrderDetail.OrderDetailResponse;
import com.example.orderservice.Model.OrderDetail;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChackOutResponse {
    private UUID id;
    private UUID id_user;
    private String location;
    private LocalDate orderTime;
    private LocalDate complated_at;
    private Long totalPrice;
    private List<OrderDetailResponse> order;


}
