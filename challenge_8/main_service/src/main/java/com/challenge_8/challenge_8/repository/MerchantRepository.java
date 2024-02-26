package com.challenge_8.challenge_8.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge_8.challenge_8.entity.Merchant;

@Repository
public interface MerchantRepository extends ListCrudRepository<Merchant, UUID>, JpaSpecificationExecutor<Merchant> {

}
