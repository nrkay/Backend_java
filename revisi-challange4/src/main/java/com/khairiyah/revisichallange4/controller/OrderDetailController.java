package com.khairiyah.revisichallange4.controller;
import com.khairiyah.revisichallange4.dto.responhandler.ResponHandler;
import com.khairiyah.revisichallange4.model.OrderDetail;
import com.khairiyah.revisichallange4.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService service;

    @Autowired
    private ModelMapper modelMapper;
    //add
    @PostMapping("/{idOrder}/{idproduct}")
    public ResponseEntity<Object> createData (@PathVariable("idOrder") UUID idOrder,
                                              @PathVariable("idproduct") UUID idproduct,
                                              @RequestBody OrderDetail request){
        Optional<OrderDetail> response = Optional.ofNullable(service.create(idOrder, idproduct, request));
        if (response.isPresent()){
            return ResponHandler.responsePost("Successfully Add Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateData(@PathVariable("id") UUID id, @RequestBody OrderDetail request){
        Optional<OrderDetail> response = Optional.ofNullable(service.update(id, request));
        if(response.isPresent()){
            return ResponHandler.responsePost("Successfully Edit Data", HttpStatus.OK, response.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable("id") UUID id){
        service.remove(id);
        return ResponHandler.responsePost("Successfully Delete Data", HttpStatus.OK, "delete data id :");
    }

}