package com.example.challange5.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "Merchant")
@SQLDelete(sql = "UPDATE Merchant SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class Merchant extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name_merchant;
    private String location;
    private Boolean open;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant")
    private List<Product> productList;


}
