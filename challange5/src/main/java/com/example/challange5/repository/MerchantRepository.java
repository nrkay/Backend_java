package com.example.challange5.repository;

import com.example.challange5.model.Merchant;
import com.example.challange5.model.dto.ReportDayMerchantResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    List<Merchant> findByOpenTrue();
    @Query("SELECT NEW com.example.challange5.model.dto.ReportDayMerchantResponse(m.name_merchant, c.id, c.orderTime, m.location, c.totalPrice) " +
            "FROM OrderDetail d " +
            "INNER JOIN d.customerOrder c " +
            "INNER JOIN d.product p " +
            "INNER JOIN p.merchant m " +
            "WHERE c.completed = true AND m.id = :id AND c.complated_at = :complated_at")
    List<ReportDayMerchantResponse> findReportsDaily(UUID id, LocalDate complated_at);
    @Query("SELECT NEW com.example.challange5.model.dto.ReportDayMerchantResponse(m.name_merchant, c.id, " +
            "c.orderTime, m.location, c.totalPrice) " +
            "FROM OrderDetail d " +
            "INNER JOIN d.customerOrder c " +
            "INNER JOIN d.product p " +
            "INNER JOIN p.merchant m " +
            "WHERE c.completed = true AND m.id = :id AND c.complated_at BETWEEN :startDate AND :endDate")
    List<ReportDayMerchantResponse> findReportsCustome(UUID id, LocalDate startDate, LocalDate endDate);
}
