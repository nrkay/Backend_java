package com.example.ordermerchant.dto.Product;


import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullOrderByProduct {
    private UUID id;
    private String name;
    List<OrderCompleted> orderCompleteds;
}
