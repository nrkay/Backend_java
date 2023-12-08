package com.example.ordermerchant.dto.Merchant;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantResponse {
    private UUID id;
    private String name_merchant;
    private String location;
    private Boolean open;
}
