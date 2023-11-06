package com.example.challange5.service;

import com.example.challange5.model.Merchant;
import com.example.challange5.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public Merchant save(Merchant merchant){
        return merchantRepository.save(merchant);
    }

    public void remove(UUID id){
        merchantRepository.deleteById(id);
    }

    public Merchant updateMerchant(UUID id, Merchant merchant) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(id);
        if (merchantOptional.isPresent()) {
            Merchant existingMerchant = merchantOptional.get();
            existingMerchant.setName_merchant(merchant.getName_merchant());
            existingMerchant.setLocation(merchant.getLocation());
            existingMerchant.setOpen(merchant.getOpen());
            log.info("update merchant existingMerchant : {}", existingMerchant);
            Merchant merchantUpdate = merchantRepository.save(existingMerchant);
            log.info("update merchant : {}", merchantUpdate.getName_merchant());
            return merchantUpdate;
        } else {
            return null; // Atau Anda dapat melempar exception untuk menunjukkan bahwa merchant dengan ID yang diberikan tidak ditemukan.
        }
    }




}
