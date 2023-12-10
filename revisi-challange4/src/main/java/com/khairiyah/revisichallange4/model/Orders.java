package com.khairiyah.revisichallange4.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khairiyah.revisichallange4.dto.auditModel.AuditModel;
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
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Orders extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate orderTime;
    private Boolean completed;
    private Long totalPrice;
    private LocalDate complated_at;
    private String location;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}