package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetail {

    //private int id;
    private String product_name;
    private int qty;
    private Long total_price;

    public OrderDetail(String product, int qty, Long price){
        this.product_name = product;
        this.qty = qty;
        this.total_price = price;
    }
}
