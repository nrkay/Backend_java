package com.example.revisichallange3.service;

import com.example.revisichallange3.dto.merchant.MerchantRequest;
import com.example.revisichallange3.dto.merchant.MerchantResponse;
import com.example.revisichallange3.model.Merchant;
import com.example.revisichallange3.repository.MerchantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MerchantServiceTest {

    @Mock
    private MerchantRepository merchantRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MerchantService merchantService;


    @Test
    void saveMerchant_success() {
        // Persiapan data uji
        MerchantRequest merchantRequest = new MerchantRequest();
        merchantRequest.setName_merchant("Nama Merchant");
        merchantRequest.setLocation("Location");
        merchantRequest.setOpen(false);
        Merchant merchant = new Merchant();
        MerchantResponse merchantResponse = merchantService.save(merchantRequest);
        // Pemalsuan perilaku repository
        given(merchantRepository.save(any(Merchant.class))).willReturn(merchant);
    }

    @Test
    void updateMerchant_success() {
        // Persiapan data uji
        UUID merchantId = UUID.randomUUID();
        MerchantRequest merchantRequest = new MerchantRequest();
        merchantRequest.setName_merchant("Updated Merchant");
        merchantRequest.setLocation("Updated Location");
        merchantRequest.setOpen(true);

        // Persiapan objek yang akan dihasilkan oleh repository (dalam kondisi sudah ada di database)
        Merchant existingMerchant = new Merchant();
        existingMerchant.setId(merchantId);
        existingMerchant.setName_merchant("Original Merchant");
        existingMerchant.setLocation("Original Location");
        existingMerchant.setOpen(false);

        // Pemalsuan perilaku repository
        given(merchantRepository.findById(merchantId)).willReturn(Optional.of(existingMerchant));
        given(merchantRepository.save(any(Merchant.class))).willReturn(existingMerchant);

        // Pemanggilan metode update dari layanan
        MerchantResponse merchantResponse = merchantService.update(merchantId, merchantRequest);

        // Periksa hasil
        assertEquals(merchantId, merchantResponse.getId());
        assertEquals("Updated Merchant", merchantResponse.getName_merchant());
        assertEquals("Updated Location", merchantResponse.getLocation());
        assertEquals(true, merchantResponse.getOpen());
    }

    @Test
    void updateMerchant_notFound() {
        // Persiapan data uji
        UUID nonExistentMerchantId = UUID.randomUUID();
        MerchantRequest merchantRequest = new MerchantRequest();
        merchantRequest.setName_merchant("Updated Merchant");
        merchantRequest.setLocation("Updated Location");
        merchantRequest.setOpen(true);

        // Pemalsuan perilaku repository
        given(merchantRepository.findById(nonExistentMerchantId)).willReturn(Optional.empty());

        // Pemanggilan metode update dari layanan
        MerchantResponse merchantResponse = merchantService.update(nonExistentMerchantId, merchantRequest);

        // Periksa hasil
        assertNull(merchantResponse);
    }
    @Test
    void deleteMerchant_success() {
        // Persiapan data uji
        UUID merchantId = UUID.randomUUID();

        // Persiapan objek yang akan dihasilkan oleh repository (dalam kondisi sudah ada di database)
        Merchant existingMerchant = new Merchant();
        existingMerchant.setId(merchantId);

        // Pemalsuan perilaku repository
        given(merchantRepository.findById(merchantId)).willReturn(Optional.of(existingMerchant));

        // Pemanggilan metode delete dari layanan
        merchantService.delete(merchantId);

        // Verifikasi bahwa metode delete pada repository dipanggil dengan benar
        verify(merchantRepository).delete(existingMerchant);
    }

    @Test
    void deleteMerchant_notFound() {
        // Persiapan data uji
        UUID nonExistentMerchantId = UUID.randomUUID();

        // Pemalsuan perilaku repository
        given(merchantRepository.findById(nonExistentMerchantId)).willReturn(Optional.empty());

        // Pemanggilan metode delete dari layanan
        merchantService.delete(nonExistentMerchantId);

        // Verifikasi bahwa metode delete pada repository tidak dipanggil (karena data tidak ditemukan)
        verify(merchantRepository).findById(nonExistentMerchantId);
        verify(merchantRepository).delete(any(Merchant.class)); // Harapannya, tidak ada pemanggilan delete
    }


}
