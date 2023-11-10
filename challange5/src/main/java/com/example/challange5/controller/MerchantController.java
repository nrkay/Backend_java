package com.example.challange5.controller;
import com.example.challange5.model.Merchant;
import com.example.challange5.model.Respon.ResponData;
import com.example.challange5.model.Respon.ResponReportsCustome;
import com.example.challange5.model.Respon.ResponReportsDaily;
import com.example.challange5.model.dto.MerchantResponse;
import com.example.challange5.model.dto.ReportDayMerchantResponse;
import com.example.challange5.repository.MerchantRepository;
import com.example.challange5.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ModelMapper modelMapper;




    //find By Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponData<MerchantResponse>> findById(@PathVariable("id") UUID id){
        MerchantResponse response = merchantService.findById(id);
        ResponData<MerchantResponse> merchantResponseResponData = new ResponData<>();
        if (response != null){
            merchantResponseResponData.setStatus("Success");
            merchantResponseResponData.setData(response);
            log.info("ini dari controller get succes : {}", response.getName_merchant());
            return ResponseEntity.ok(merchantResponseResponData);
        } else {
            merchantResponseResponData.setStatus("Data not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(merchantResponseResponData);
        }
    }

    //filter open merchant
    @GetMapping("/openMerchant")
    public ResponseEntity<ResponData<List<MerchantResponse>>> findOpenMerchat(){
        List<MerchantResponse> responseData = merchantService.findOpenMerchant();
        ResponData<List<MerchantResponse>> merchantResponseResponData = ResponData.<List<MerchantResponse>>builder()
                .status("Success")
                .message("Data found successfully")
                .data(responseData)
                .build();

        return ResponseEntity.ok(merchantResponseResponData);
    }



    @PostMapping
    public ResponseEntity<ResponData<MerchantResponse>> create(@RequestBody Merchant merchant){
        Merchant merchant1 = merchantService.save(merchant);
        ResponData<MerchantResponse> merchantResponseResponData = new ResponData<>();
        merchantResponseResponData.setStatus("add data success");
        //koversi menggunakan modelMapper
        MerchantResponse merchantResponse = modelMapper.map(merchant1, MerchantResponse.class);
        merchantResponseResponData.setData(merchantResponse);
        return ResponseEntity.ok(merchantResponseResponData);

    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") UUID id){
//        merchantService.remove(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") UUID id){
        Map<String, String> responseMessage = new HashMap<>();
        MerchantResponse merchantResponse = merchantService.remove(id);
        if (Objects.nonNull(merchantResponse)){
            responseMessage.put("message", "Data berhasil dihapus.");
            return ResponseEntity.ok(responseMessage);
        } else {
            responseMessage.put("error", "Data Not Found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Merchant> update(@PathVariable("id") UUID id, @RequestBody Merchant merchant) {
//        Merchant updatedMerchant = merchantService.updateMerchant(id, merchant);
//
//        if (updatedMerchant != null) {
//            return ResponseEntity.ok(updatedMerchant);
//        } else {
//            return ResponseEntity.notFound().build(); // Atau Anda dapat melempar exception untuk menunjukkan bahwa merchant dengan ID yang diberikan tidak ditemukan.
//        }
//    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponData<MerchantResponse>> update(@PathVariable("id") UUID id, @RequestBody Merchant merchant) {
        MerchantResponse updatedMerchant = merchantService.updateMerchant(id, merchant);
        ResponData<MerchantResponse> merchantResponseResponData = new ResponData<>();
        if (updatedMerchant != null) {
            merchantResponseResponData.setStatus("update data success");
            merchantResponseResponData.setData(updatedMerchant);
            log.info("ini dari controller edit succes : {}", updatedMerchant);
            return ResponseEntity.ok(merchantResponseResponData);
        } else {
            merchantResponseResponData.setStatus("Data not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(merchantResponseResponData);
        }
    }


    //reporting merchant perhari

    //algortima:
    //cari dulu order dengan status completed true dimana memiliki hubungan dengan merchat a
    //kalau uda dapat, berati dalam bentuk List, Hitung semua total pendapatan dihari itu
    @GetMapping("/dailyReports/")
    public ResponseEntity<ResponData<ResponReportsDaily<List<ReportDayMerchantResponse>>>>
    dailyReport(@RequestParam("id") UUID id,
                @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate complated_at){
        ResponData<ResponReportsDaily<List<ReportDayMerchantResponse>>> response = merchantService.dailyReport(id, complated_at);
        if (Objects.nonNull(response)){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/customeReports")
    public ResponseEntity<ResponData<ResponReportsCustome<List<ReportDayMerchantResponse>>>>
    customeReports(@RequestParam("id") UUID id,
                   @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
                   @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date){
        ResponData<ResponReportsCustome<List<ReportDayMerchantResponse>>> response = merchantService.customeReport(id,
                start_date, end_date);
        if (Objects.nonNull(response)){
            return ResponseEntity.ok(response);
        } else {
            log.info("GAGAL SUDAH");
            return ResponseEntity.badRequest().build();
        }
    }




}
