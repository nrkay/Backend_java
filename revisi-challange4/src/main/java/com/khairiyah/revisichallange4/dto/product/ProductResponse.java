package com.khairiyah.revisichallange4.dto.product;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private UUID id;
    private String name;
    private Long price;
    private UUID merhant_id;
}