package com.example.challange5.service;

import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.Merchant;
import com.example.challange5.model.Respon.ResponData;
import com.example.challange5.model.Respon.ResponReportsCustome;
import com.example.challange5.model.Respon.ResponReportsDaily;
import com.example.challange5.model.dto.MerchantResponse;
import com.example.challange5.model.dto.ReportDayMerchantResponse;
import com.example.challange5.repository.CustomerOrderRepository;
import com.example.challange5.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;


    public MerchantResponse findById(UUID id){
        Optional<Merchant> response = merchantRepository.findById(id);
        if (response.isPresent()){
                //konversi merchant ke merchantRespon dengan ModelMapper
                MerchantResponse merchantResponse = modelMapper.map(response.get(), MerchantResponse.class);
                log.info("find merchant is success {}", merchantResponse.getName_merchant());
                return merchantResponse;
        } else {
            log.info("data not found");
            return null;
        }
    }

    //find merchant open
    public List<MerchantResponse> findOpenMerchant(){
        List<Merchant> openMerchants = merchantRepository.findByOpenTrue();
        List<MerchantResponse> merchantResponses = openMerchants.stream()
                .map(merchant -> modelMapper.map(merchant, MerchantResponse.class))
                .collect(Collectors.toList());
        return merchantResponses;
//        ResponData responData = modelMapper.map(Merchant, MerchantResponse.class);
//        return merchantRepository.findByOpenTrue();
    }

    public Merchant save(Merchant merchant){
        return merchantRepository.save(merchant);
    }

    public MerchantResponse remove(UUID id){
        Optional<Merchant> respon = merchantRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                Merchant merchantRespon = respon.get();
                merchantRepository.deleteById(id);

                //konversi merchant ke merchantRespon dengan ModelMapper
                MerchantResponse merchantResponse = modelMapper.map(merchantRespon, MerchantResponse.class);
                log.info("delete merchant is success {}", merchantResponse);
                return merchantResponse;
            } else {
                log.info("data was deleted");
                return null;
            }
        } else {
            log.info("data not found");
            return null;
        }

    }

//    public Merchant updateMerchant(UUID id, Merchant merchant) {
//        Optional<Merchant> merchantOptional = merchantRepository.findById(id);
//        if (merchantOptional.isPresent()) {
//            Merchant existingMerchant = merchantOptional.get();
//            existingMerchant.setName_merchant(merchant.getName_merchant());
//            existingMerchant.setLocation(merchant.getLocation());
//            existingMerchant.setOpen(merchant.getOpen());
//            log.info("update merchant existingMerchant : {}", existingMerchant);
//            Merchant merchantUpdate = merchantRepository.save(existingMerchant);
//            log.info("update merchant : {}", merchantUpdate.getName_merchant());
//            return merchantUpdate;
//        } else {
//            return null; // Atau Anda dapat melempar exception untuk menunjukkan bahwa merchant dengan ID yang diberikan tidak ditemukan.
//        }
//    }

    public MerchantResponse updateMerchant(UUID id, Merchant merchant) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(id);
        if (merchantOptional.isPresent()) {
            Merchant existingMerchant = merchantOptional.get();
            existingMerchant.setName_merchant(merchant.getName_merchant());
            existingMerchant.setLocation(merchant.getLocation());
            existingMerchant.setOpen(merchant.getOpen());
            //menyimpan editan
            Merchant merchantUpdate = merchantRepository.save(existingMerchant);

            //konversi dto
            MerchantResponse merchantResponse = modelMapper.map(merchantOptional.get(), MerchantResponse.class);
            log.info("update merchant existingMerchant : {}", merchantResponse.getName_merchant());
            log.info("update merchant : {}", merchantUpdate.getName_merchant());
            return merchantResponse;
        } else {
            return null;
        }
    }

    //algortima:
    //cari dulu order dengan status completed true dimana memiliki hubungan dengan merchat a
    //kalau uda dapat, berati dalam bentuk List, Hitung semua total pendapatan dihari itu

//    private void dailyReport(UUID id){
//        //mencari order yang sudah berhasil chackout(complated = true)
//        List<CustomerOrder> orderIsTrue = customerOrderRepository.findByCompletedTrue();
//        if (orderIsTrue.isEmpty()){
//            //kalau kosong kenapa
//        } else {
//
//            if ()
//        }
//    }

//    public List<ReportDayMerchantResponse> dailyReport(UUID id, LocalDate completed_at){
//        List<ReportDayMerchantResponse> response = merchantRepository.findReportsDaily(id, completed_at);
//        if (response.isEmpty()){
//            log.info("WOE KOSONG INI, ETDAH");
//            return null;
//        } else {
//            log.info("berhasil");
//            return response;
//        }
//    }

    public ResponData<ResponReportsDaily<List<ReportDayMerchantResponse>>> dailyReport(UUID id, LocalDate completed_at){
        Optional<Merchant> getMerchant = merchantRepository.findById(id);
        ResponData<ResponReportsDaily<List<ReportDayMerchantResponse>>> bodyRespon = new ResponData<>();
        ResponReportsDaily<List<ReportDayMerchantResponse>> responseDataOrderList = new ResponReportsDaily<>();
        //mengisi responseDataOrderList
        Long totalIncome = 0L;
        responseDataOrderList.setId(getMerchant.get().getId());
        responseDataOrderList.setName_merchant(getMerchant.get().getName_merchant());
        responseDataOrderList.setDate(completed_at);

        List<ReportDayMerchantResponse> response = merchantRepository.findReportsDaily(id, completed_at);
        if (response.isEmpty()){
            log.info("null");
            bodyRespon.setStatus("Success");
            bodyRespon.setMessage("Not Found Order in Merchant");
            bodyRespon.setData(responseDataOrderList);
            responseDataOrderList.setTotalIncome(null);
            responseDataOrderList.setListOrder(null);
            return bodyRespon;
        } else {
            //mengisi bodyRespon
            bodyRespon.setStatus("Success");
            bodyRespon.setMessage("Get Report Daily is Success");
            bodyRespon.setData(responseDataOrderList);

            //mengisi responseDataOrderList
            //menghitung income dari semua totalPrice Order
            for (ReportDayMerchantResponse reportDayMerchantResponse : response){
                totalIncome += reportDayMerchantResponse.getTotalPrice();
            }
            log.info("total income nya : {}", totalIncome);
            responseDataOrderList.setTotalIncome(totalIncome);
            responseDataOrderList.setListOrder(response);
            log.info("berhasil");
            return bodyRespon;
        }
    }

    public ResponData<ResponReportsCustome<List<ReportDayMerchantResponse>>> customeReport(UUID id, LocalDate startDate, LocalDate endDate){
        Optional<Merchant> getMerchant = merchantRepository.findById(id);
        ResponData<ResponReportsCustome<List<ReportDayMerchantResponse>>> bodyRespon = new ResponData<>();
        ResponReportsCustome<List<ReportDayMerchantResponse>> responseDataOrderList = new ResponReportsCustome<>();

        //mengisi responseDataOrderList
        Long totalIncome = 0L;
        responseDataOrderList.setEnd_date(endDate);
        responseDataOrderList.setStart_date(startDate);
        responseDataOrderList.setId(id);
        responseDataOrderList.setName_merchant(getMerchant.get().getName_merchant());
        List<ReportDayMerchantResponse> response = merchantRepository.findReportsCustome(id,startDate,endDate);
        if (response.isEmpty()){
            log.info("WOE INI NULLL LOH");
            bodyRespon.setStatus("Success");
            bodyRespon.setMessage("Not Found Order in Merchant");
            bodyRespon.setData(responseDataOrderList);
            responseDataOrderList.setTotalIncome(null);
            responseDataOrderList.setListOrder(null);
            return bodyRespon;
        } else {
            //mengisi bodyRespon
            log.info("WKWKWK INI ADA");

            bodyRespon.setStatus("Success");
            bodyRespon.setMessage("Get Report Daily is Success");
            bodyRespon.setData(responseDataOrderList);
            //mengisi responseDataOrderList
            //menghitung income dari semua totalPrice Order
            for(ReportDayMerchantResponse reportDayMerchantResponse : response){
                totalIncome += reportDayMerchantResponse.getTotalPrice();
            }
            responseDataOrderList.setTotalIncome(totalIncome);
            responseDataOrderList.setListOrder(response);
            return bodyRespon;
        }
    }




}
