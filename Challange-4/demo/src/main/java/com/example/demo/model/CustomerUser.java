package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "customer_user")
public class CustomerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String passsword;
    //for get all user order
    //mappedBy diisi field yang merujuk ke User di Order
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CustomerOrder> orderList;

}
