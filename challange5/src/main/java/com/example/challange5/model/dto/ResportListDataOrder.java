package com.example.challange5.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResportListDataOrder {
    private UUID id;
    private LocalDate orderTime;
    private String Location;
    private String username;
    private Long totalPrice;
}
