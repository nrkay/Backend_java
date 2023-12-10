package com.khairiyah.revisichallange4.repository;

import com.khairiyah.revisichallange4.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    @Procedure("editMerchant")
    Merchant editMerchant(@Param("p_name_merchant") String nameMerchant,
                      @Param("p_open") boolean isOpen,
                      @Param("p_location") String location,
                      @Param("p_merchant_id") UUID id);

    @Procedure("addMerchant")
    Merchant addMerchant(@Param("p_name_merchant") String nameMerchant,
                          @Param("p_open") boolean isOpen,
                          @Param("p_location") String location);

    @Procedure("deleteMerchantById")
    void deleteMerchant(@Param("merchant_id") UUID id);
}