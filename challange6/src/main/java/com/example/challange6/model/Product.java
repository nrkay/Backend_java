package com.example.challange6.model;


import com.example.challange6.model.auditModel.AuditModelConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Product extends AuditModelConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Long price;
    private Boolean active;
}
