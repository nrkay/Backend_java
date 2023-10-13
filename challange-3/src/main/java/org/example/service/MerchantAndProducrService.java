package org.example.service;

import org.example.model.Data;
import org.example.model.Merchant;
import org.example.model.Product;

public class MerchantAndProducrService {
//    public void initiateData(){
//        Data.merchant.add(new Merchant("Warung Nasi Goreng Pak Dul", "Jln. in Yuk"));
//        Data.merchant.add(new Merchant("Seger", "Jln. Cendrawasih"));
//        Data.merchant.add(new Merchant("Rumah Makan Nasi Padang", "Jln. Padang Utara"));
//
//        Data.products.add(new Product(1,"Nasi Goreng Ampela", 20000, findMerchantByName("Warung Nasi Goreng Pak Dul")));
//        Data.products.add(new Product(2, "Mie Goreng", 18000, findMerchantByName("Warung Nasi Goreng Pak Dul")));
//        Data.products.add(new Product(3, "Thai Tea", 5000, findMerchantByName("Seger")));
//        Data.products.add(new Product(4, "Es Teh", 6000, findMerchantByName("Seger")));
//        Data.products.add(new Product(5, "Nasi Rendang", 15000, findMerchantByName("Rumah Makan Nasi Padang")));
//        Data.products.add(new Product(6, "Nasi Kapau", 25000, findMerchantByName("Rumah Makan Nasi Padang")));
//        Data.products.add(new Product(7, "Gulai Ayam + Nasi", 20000, findMerchantByName("Rumah Makan Nasi Padang")));
//

    //}

    public Merchant findMerchantByName(String merchantName) {
        for (Merchant data : Data.merchant) {
            if (data.getMerchant_name().equals(merchantName)) {
                return data;
            }
        }
        return null; // Return null jika tidak ditemukan
    }








}
