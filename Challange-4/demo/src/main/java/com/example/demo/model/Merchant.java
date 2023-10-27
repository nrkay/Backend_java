package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
//Anotasi ini digunakan untuk menghasilkan metode "setter" yang mengembalikan objek itu sendiri (objek yang dimodifikasi) sehingga Anda dapat menggabungkan beberapa panggilan setter ke dalam satu baris.
@Accessors(chain = true)
//anotation untuk merepresentasikan sebuah table
@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String location;
    private boolean open;

    //for get all product in merchant
    // mappedBy diisi field yang merujuk ke Merchant di Product
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant")
    private List<Product> productList;

//    @Override
//    public String toString() {
//        return "Merchant{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", location='" + location + '\'' +
//                ", open=" + open +
//                ", productList=" + productList +
//                '}';
//    }
}
