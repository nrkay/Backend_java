package com.example.demo.service;

import com.example.demo.model.Merchant;
import com.example.demo.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantService {

    //disebut field injecton
    @Autowired
    MerchantRepository merchantRepository;


    //untuk mendapatkan data, kita butuh data dari DAO yg diambil dri repositori
    public List<Merchant> getAll() {
        //method untuk mendapatkan semua value di table Merchant
        return merchantRepository.findAll();
    }

    public Merchant create(Merchant merchant){
        merchant.setOpen(true);
        return merchantRepository.save(merchant);
    }

    public Merchant findMerchantById(UUID merchantId){
        Optional<Merchant> optionalMerchant = merchantRepository.findById(merchantId);
        if (optionalMerchant.isPresent()){
            return optionalMerchant.get();
        } else {
            throw new EntityNotFoundException("Merchant not found with ID: " + merchantId);
        }
    }

    //public List<Merchant> getOpenMerchant(){
//        return merchantRepository.getOpenMerchant();
//    }

    public void editMerchant(String name, boolean isOpen){
        merchantRepository.editMerchant(name, isOpen);
    }
}
