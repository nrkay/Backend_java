package com.example.challange5.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDayMerchantResponse{
    private String name_merchant;
    private UUID id;
    private LocalDate orderTime;
    private String location;
    private Long totalPrice;
}
