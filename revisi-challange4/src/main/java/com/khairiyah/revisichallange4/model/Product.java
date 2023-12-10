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
//Anotasi ini digunakan untuk menghasilkan metode "setter" yang mengembalikan objek itu sendiri (objek yang dimodifikasi) sehingga Anda dapat menggabungkan beberapa panggilan setter ke dalam satu baris.
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Product extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Long price;
    @ManyToOne(targetEntity = Merchant.class)
    @JoinColumn(name = "merhant_id")
    @JsonIgnore
    private Merchant merchant;
}