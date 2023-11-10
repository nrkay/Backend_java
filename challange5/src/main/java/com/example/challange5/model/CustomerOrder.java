package com.example.challange5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
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
public class CustomerOrder extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate orderTime;
    private Boolean completed;
    private Long totalPrice;
    private LocalDate complated_at;
    private String location;
    @ManyToOne(targetEntity = CustomerUser.class)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private CustomerUser user;
}
