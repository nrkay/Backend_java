package com.khairiyah.revisichallange4.service;

import com.khairiyah.revisichallange4.advance.handleException.DataNotFoundException;
import com.khairiyah.revisichallange4.dto.merchant.MerchantRequest;
import com.khairiyah.revisichallange4.dto.merchant.MerchantResponse;
import com.khairiyah.revisichallange4.model.Merchant;
import com.khairiyah.revisichallange4.repository.MerchantRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private ModelMapper modelMapper;

    // find by id
    public Merchant findById(UUID id){
        return merchantRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Data is Not Found"));
    }

    //find All
    public List<MerchantResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Merchant> merchantPage = merchantRepository.findAll(pageable);

        List<MerchantResponse> merchantResponses = merchantPage.getContent().stream()
                .map(merchant -> modelMapper.map(merchant, MerchantResponse.class))
                .collect(Collectors.toList());

        return merchantResponses;
    }

    public long count() {
        return merchantRepository.count();
    }
    // add data
    public MerchantResponse save(MerchantRequest request){
        Merchant req =  merchantRepository.save(modelMapper.map(request, Merchant.class));
        return modelMapper.map(req, MerchantResponse.class);
    }
    // update data
    public MerchantResponse update(UUID id, MerchantRequest merchant){
        Optional<Merchant> merchant1 = merchantRepository.findById(id);
        if (Objects.nonNull(merchant1)){
            Merchant findMerchant = merchant1.get();
            findMerchant.setName_merchant(merchant.getName_merchant());
            findMerchant.setLocation(merchant.getLocation());
            findMerchant.setOpen(merchant.getOpen());
            Merchant merchantUpdate = merchantRepository.save(findMerchant);
            return modelMapper.map(merchantUpdate, MerchantResponse.class);
        } else {
            return null;
        }
    }


    // delete data
    public void delete(UUID id){
        Optional<Merchant> merchant1 = merchantRepository.findById(id);
        if (Objects.nonNull(merchant1)){
            Merchant response = merchant1.get();
            merchantRepository.delete(response);
        }
    }


}