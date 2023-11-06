package com.example.challange5.model.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantResponse {
    private UUID id;
    private String name;
    private String location;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
