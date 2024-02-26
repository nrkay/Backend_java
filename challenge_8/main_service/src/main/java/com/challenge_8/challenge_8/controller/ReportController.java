package com.challenge_8.challenge_8.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.challenge_8.challenge_8.dto.response.OrderDto;
import com.challenge_8.challenge_8.service.JasperReportService;
import com.challenge_8.challenge_8.service.OrderService;
import com.challenge_8.challenge_8.utils.ResponseHandler;

@Controller
@RequestMapping("/v1/report")
public class ReportController {
    @Autowired
    OrderService orderService;

    @Autowired
    JasperReportService jasperReportService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getItemReport(@PathVariable UUID orderId,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            OrderDto order = orderService.getOrderById(orderId, userDetails);
            byte[] reportContent = jasperReportService.getItemReport(order, "pdf");

            ByteArrayResource resource = new ByteArrayResource(reportContent);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resource.contentLength())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                    .filename("item-report." + "pdf")
                                    .build().toString())
                    .body(resource);
        } catch (Exception e) {
            return ResponseHandler.generateResponseFailed(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}