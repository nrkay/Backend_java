package com.example.ordermerchant.dto.Merchant;

import lombok.*;

import java.util.UUID;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRequest {
    private String name_merchant;
    private String location;
    private Boolean open;
}
