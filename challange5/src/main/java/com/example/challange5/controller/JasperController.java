package com.example.challange5.controller;

import com.example.challange5.model.dto.ReportDayMerchantResponse;
import com.example.challange5.repository.MerchantRepository;
import com.example.challange5.service.InvoiceService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/document")
public class JasperController {
    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    InvoiceService invoiceService;

//    @GetMapping("report-merchant")
//    public ResponseEntity<Resource> getReportMerchant(
//                                                      @RequestParam("format") String format,
//                                                      @RequestParam("start_date")
//                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
//                                                      @RequestParam("end_date")
//                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date,
//                                                      @RequestParam("id") UUID id) throws JRException, IOException {
//        //mendapatkan data
//        List<ReportDayMerchantResponse> itemList = merchantRepository.findReportsCustome(id, start_date, end_date);
//
//        //olah data di jasper
//        byte[] reportContent = invoiceService.generateInvoice(itemList, format);
//        ByteArrayResource resource = new ByteArrayResource(reportContent);
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentLength(resource.contentLength())
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        ContentDisposition.attachment()
//                                .filename("item-report." + format)
//                                .build().toString())
//                .body(resource);
//
//    }

}
