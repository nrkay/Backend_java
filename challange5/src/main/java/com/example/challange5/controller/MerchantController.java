package com.example.challange5.controller;
import com.example.challange5.model.Merchant;
import com.example.challange5.repository.MerchantRepository;
import com.example.challange5.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public Merchant create(@RequestBody Merchant merchant){
        return merchantService.save(merchant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        merchantService.remove(id);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Merchant> update(@PathVariable("id") UUID id, @RequestBody Merchant merchant){
//        Merchant responMerchant = merchant.updateMerchant(id, merchant);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Merchant> update(@PathVariable("id") UUID id, @RequestBody Merchant merchant) {
        Merchant updatedMerchant = merchantService.updateMerchant(id, merchant);

        if (updatedMerchant != null) {
            return ResponseEntity.ok(updatedMerchant);
        } else {
            return ResponseEntity.notFound().build(); // Atau Anda dapat melempar exception untuk menunjukkan bahwa merchant dengan ID yang diberikan tidak ditemukan.
        }
    }

}
