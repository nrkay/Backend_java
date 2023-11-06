package com.example.challange5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Anotasi ini digunakan untuk menghasilkan metode "setter" yang mengembalikan objek itu sendiri (objek yang dimodifikasi) sehingga Anda dapat menggabungkan beberapa panggilan setter ke dalam satu baris.
@Entity
@Table(name = "product")
public class Product extends AuditModel{
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
