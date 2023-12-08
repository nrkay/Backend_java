package com.example.ordermerchant.service;

import com.example.ordermerchant.advice.advice.DataNotFoundException;
import com.example.ordermerchant.dto.Merchant.MerchantRequest;
import com.example.ordermerchant.dto.Merchant.MerchantResponse;
import com.example.ordermerchant.model.Merchant;
import com.example.ordermerchant.repository.MerchantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


    //get all data
    public List<MerchantResponse> getAll() {
        List<Merchant> response =merchantRepository.findAll();
        return response.stream().
                map(data -> modelMapper.map(data, MerchantResponse.class))
                .collect(Collectors.toList());
    }

    // find by id
    public Merchant findById(UUID id){
        return merchantRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Data is Not Found"));
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
