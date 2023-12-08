package com.example.ordermerchant.dto.Product;

import com.example.ordermerchant.model.Merchant;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private Long price;
}
