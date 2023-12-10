package com.example.demo.repository;


import com.example.demo.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//@Repository adalah komponen yang bertanggung jawab untuk mengakses data dari penyimpanan yang mendasarinya,
//seperti database. Mereka dapat berisi metode untuk melakukan operasi seperti menyimpan, mengambil,
// memperbarui, atau menghapus data.
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    @Procedure("editMerchant")
    void editMerchant(@Param("p_name_merchant") String name, @Param("p_open") boolean isOpen, @Param(p_location) String name, @Param(p_merchant_id) UUID id);

//    @Modifying
//    @Query("SELECT m FROM Merchant m WHERE m.open = true")
//    List<Merchant> getOpenMerchant();
}
