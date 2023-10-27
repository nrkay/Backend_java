package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    private int quantity;
    private long totalPrice;

//    @Override
//    public String toString() {
//        return "OrderDetail{" +
//                "id=" + id +
//                ", customerOrder=" + customerOrder +
//                ", product=" + product +
//                ", quantity=" + quantity +
//                ", totalPrice=" + totalPrice +
//                '}';
//    }
}
