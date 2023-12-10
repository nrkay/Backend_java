package com.khairiyah.revisichallange4.controller;

import com.khairiyah.revisichallange4.dto.merchant.MerchantRequest;
import com.khairiyah.revisichallange4.dto.merchant.MerchantResponse;
import com.khairiyah.revisichallange4.dto.responhandler.ResponHandler;
import com.khairiyah.revisichallange4.model.Merchant;
import com.khairiyah.revisichallange4.service.MerchantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController{

    @Autowired
    private MerchantService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/findAll/")
    public ResponseEntity<Page<MerchantResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<MerchantResponse> merchants = service.findAll(page, size);
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(new PageImpl<>(merchants, pageable, service.count()));
    }

    //add
    @PostMapping("/")
    public ResponseEntity<Object> createData (@RequestBody MerchantRequest request){
        Optional<MerchantResponse> response = Optional.ofNullable(service.save(request));
        if (response.isPresent()){
            return ResponHandler.responsePost("Successfully Add Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") UUID id, @RequestBody MerchantRequest merchantRequest){
        Optional<MerchantResponse> response = Optional.ofNullable(service.update(id, merchantRequest));
        if(response.isPresent()){
            return ResponHandler.responsePost("Successfully Edit Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable("id") UUID id){
        service.delete(id);
        return ResponHandler.responsePost("Successfully Delete Data", HttpStatus.OK, "delete data id :");
    }

}
