package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "customer_order")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    private LocalDate orderTime;
    private String destination;
    private boolean completed;
    @ManyToOne(targetEntity = CustomerUser.class)
    //column name in product table
    @JoinColumn(name = "user_id")
    private CustomerUser user;

}
