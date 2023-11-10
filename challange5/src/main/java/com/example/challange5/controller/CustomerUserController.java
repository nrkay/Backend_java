package com.example.challange5.controller;


import com.example.challange5.exception.DataNotFound;
import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.CustomerUser;
import com.example.challange5.model.Respon.ResponData;
import com.example.challange5.model.dto.OrderDetailResponse;
import com.example.challange5.model.dto.OrderDto.GetUserOrderResponse;
import com.example.challange5.model.dto.OrderDto.UserOrderResponse;
import com.example.challange5.model.dto.UserDto.UserResponseDto;
import com.example.challange5.service.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class CustomerUserController {
    @Autowired
    private CustomerUserService customerUserService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponData<UserResponseDto>> getById(@PathVariable("id") UUID id){
        UserResponseDto respon = customerUserService.get(id);
        ResponData<UserResponseDto> bodyRespon = new ResponData<>();
        if (respon != null){
            bodyRespon.setStatus("Success");
            bodyRespon.setMessage("Get data is Success");
            bodyRespon.setData(respon);
            return ResponseEntity.ok(bodyRespon);
        } else {
            throw new DataNotFound();
        }
    }

//    @PostMapping
//    public CustomerUser create(@RequestBody CustomerUser customerUser){
//        return customerUserService.save(customerUser);
//    }

    @PostMapping
    public ResponseEntity<ResponData<UserResponseDto>> create(@RequestBody CustomerUser customerUser){
        UserResponseDto userResponseDto = customerUserService.save(customerUser);
        ResponData<UserResponseDto> bodyRespon = new ResponData<>();
        bodyRespon.setStatus("Success");
        bodyRespon.setMessage("Add data is Success");
        bodyRespon.setData(userResponseDto);
        return ResponseEntity.ok(bodyRespon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        CustomerUser response = customerUserService.remove(id);
        if (response != null) {
            return ResponseEntity.ok("Delete Success");
        } else {
            throw new DataNotFound();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerUser> update(@PathVariable("id") UUID id, @RequestBody CustomerUser customerUser){
        CustomerUser respon = customerUserService.updateUser(id, customerUser);
        if (respon != null){
            return ResponseEntity.ok(respon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Menampilkan detail order dari user yang melakukan
    //order makanan.
//    @GetMapping("getAllOrderByUserId/{id}")
//    public ResponseEntity<ResponData<List<GetUserOrderResponse<List<OrderDetailResponse>>>>>
//    getAllOrderByUserId(@PathVariable("id") UUID id){
//        ResponData<List<GetUserOrderResponse<List<OrderDetailResponse>>>> bodyRespon = new ResponData<>();
//        List<GetUserOrderResponse<List<OrderDetailResponse>>> respon = customerUserService.getAllOrderByIdUser(id);
////        if (Objects.nonNull(respon)){
////            bodyRespon.setStatus("Success");
////            bodyRespon.setMessage("Get Data Success");
////            bodyRespon.setData(respon);
////            return ResponseEntity.ok(bodyRespon);
////        } else {
////            throw new DataNotFound();
////        }
//        if (respon.isEmpty()){
//            throw new DataNotFound();
//        } else {
//            bodyRespon.setStatus("Success");
//            bodyRespon.setMessage("Get Data Success");
//            bodyRespon.setData(respon);
//            return ResponseEntity.ok(bodyRespon);
//
//        }
//    }
}
