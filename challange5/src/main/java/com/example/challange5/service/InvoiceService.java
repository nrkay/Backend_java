package com.example.challange5.service;

import com.example.challange5.model.dto.ReportDayMerchantResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class InvoiceService {
    public byte[] generateInvoice(List<ReportDayMerchantResponse> items, String format){
        JasperReport jasperReport;
        try {
            jasperReport = (JasperReport) JRLoader.loadObject(ResourceUtils.getFile("item-report.jasper"));
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:jasper/item-report.jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, "item-report.jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException("Gagal memuat atau mengkompilasi laporan: " + ex.getMessage(), ex);
            }
        }

        //menghitung total Invoice
            Long totalIncome = 0l;
            for (ReportDayMerchantResponse reportDayMerchantResponse : items){
                totalIncome += reportDayMerchantResponse.getTotalPrice();
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
            Map<String, Object> parameters = new HashMap<>();
             parameters.put("total", (Object) totalIncome);
            JasperPrint jasperPrint = null;
            byte[] reportContent;

            try {
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                switch (format) {
                    case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
                    case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
                    default -> throw new RuntimeException("Unknown report format");
                }
            } catch (JRException e){
                throw new RuntimeException(e);
            }
            return reportContent;
    }
}
