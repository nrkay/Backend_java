package com.example.orderservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "customer_order")
@SQLDelete(sql = "UPDATE customer_order SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Order extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate orderTime;
    private Boolean completed;
    private Long totalPrice;
    private LocalDate complated_at;
    private String location;
    private UUID id_user;

    @OneToMany(mappedBy="order")
    private List<OrderDetail> order;
}
