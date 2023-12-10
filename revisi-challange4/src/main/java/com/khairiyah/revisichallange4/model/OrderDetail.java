package com.khairiyah.revisichallange4.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khairiyah.revisichallange4.dto.auditModel.AuditModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "order_detail")
@SQLDelete(sql = "UPDATE order_detail SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class OrderDetail extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders customerOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
    private int quantity;
    private Long totalPrice;
}