package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
//Anotasi ini digunakan untuk menghasilkan metode "setter" yang mengembalikan objek itu sendiri (objek yang dimodifikasi) sehingga Anda dapat menggabungkan beberapa panggilan setter ke dalam satu baris.
@Accessors(chain = true)
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    //kalau Long bisa null, kalau long ga bisa null saat diinstance
    private Long price;

    @ManyToOne(targetEntity = Merchant.class)
    //column name in product table
    @JoinColumn(name = "merhant_id")
    private Merchant merchant;

//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", merchant=" + merchant +
//                '}';
//    }
}
