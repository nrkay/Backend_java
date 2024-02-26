package com.challenge_8.challenge_8.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;

import com.challenge_8.challenge_8.dto.request.CreateMerchantDto;
import com.challenge_8.challenge_8.dto.request.UpdateMerchantDto;
import com.challenge_8.challenge_8.dto.response.MerchantDto;
import com.challenge_8.challenge_8.entity.Merchant;
import com.challenge_8.challenge_8.exception.ApiException;

public interface MerchantService {
    public MerchantDto createMerchant(CreateMerchantDto request, UserDetails userDetails);

    public MerchantDto updateMerchant(UUID merchantId, UpdateMerchantDto request, UserDetails userDetails)
            throws ApiException;

    public MerchantDto getMerchantById(UUID merchantId) throws ApiException;

    public MerchantDto deleteMerchant(UUID merchantId, UserDetails userDetails) throws ApiException;

    public List<MerchantDto> getAllMerchants(Specification<Merchant> filterQueries);
}
