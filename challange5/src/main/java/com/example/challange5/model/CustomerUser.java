package com.example.challange5.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "Customer_user")
@SQLDelete(sql = "UPDATE Customer_user SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CustomerUser extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CustomerOrder> orderList;


}
