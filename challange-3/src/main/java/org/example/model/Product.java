package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private long id;
    private String product_name;
    private Long price;
    private String merchant_name;
}


