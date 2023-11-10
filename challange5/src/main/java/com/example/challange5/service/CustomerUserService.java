package com.example.challange5.service;


import com.example.challange5.exception.DataNotFound;
import com.example.challange5.model.CustomerUser;
import com.example.challange5.model.OrderDetail;
import com.example.challange5.model.dto.OrderDetailResponse;
import com.example.challange5.model.dto.OrderDto.GetUserOrderResponse;
import com.example.challange5.model.dto.OrderDto.UserOrderResponse;
import com.example.challange5.model.dto.UserDto.UserResponseDto;
import com.example.challange5.repository.CustomerUserRepository;
import com.example.challange5.repository.OrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerUserService {
    @Autowired
    private CustomerUserRepository customerUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDetailRepository orderDetailRepository;



    //get user by id
    public UserResponseDto get(UUID id){
        Optional<CustomerUser> respon = customerUserRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                log.info("user is found : {}", respon.get());
                return modelMapper.map(respon.get(), UserResponseDto.class);
            } else {
                log.info("data was deleted");
                throw new DataNotFound();
            }
        } else {
            log.info("data not found");
            throw new DataNotFound();
        }
    }

    public UserResponseDto save(CustomerUser customerUser){
        CustomerUser user = customerUserRepository.save(customerUser);
        return modelMapper.map(user, UserResponseDto.class);
//        UserResponseDto response = modelMapper.map(user, UserResponseDto.class);
//        return response;
    }

    public CustomerUser remove(UUID id){
        Optional<CustomerUser> respon = customerUserRepository.findById(id);
        if (respon.isPresent()) {
            if (respon.get().getDeleted().equals(false)) {
                CustomerUser customerUserRespon = respon.get();
                customerUserRepository.deleteById(id);
                log.info("Delete Success");
                return customerUserRespon;
            } else {
                log.info("data was deleted");
                throw new DataNotFound();
            }
        } else {
            log.info("data not found");
            throw new DataNotFound();
        }
    }

    public CustomerUser updateUser(UUID id, CustomerUser request){
        Optional<CustomerUser> getUser = customerUserRepository.findById(id);
        if (getUser.isPresent()){
            if (getUser.get().getDeleted().equals(false)){
                getUser.get().setUsername(request.getUsername());
                getUser.get().setPassword(request.getPassword());
                log.info("update user = {}", getUser);
                return customerUserRepository.save(getUser.get());
            } else {
                log.info("data was deleted");
                return null;
            }
        } else {
            log.info("data not found");
            return null;
        }
    }

    //Menampilkan detail order dari user yang melakukan
    //order makanan.
//    public List<GetUserOrderResponse<List<OrderDetailResponse>>> getAllOrderByIdUser(UUID id){
//        //cek user ada add product ke detailOrder
//        List<OrderDetail> orders = orderDetailRepository.findByCustomerOrder_Id(id);
//        List<UserOrderResponse> response = customerUserRepository.findAllOrdersbyUserId(id);
//        if (response.isEmpty()){
////            //konversi dto UserOrderResponse ke
////            GetUserOrderResponse getUserOrderResponse = modelMapper.map(response, GetUserOrderResponse.class);
////            // cek apakah user ada add order detail
////            if (orders.isEmpty()){
////                getUserOrderResponse.setData(null);
////            } else {
////                //konversi dto OrderDetail ke Order Detail Response menggunakan
////                //modelmap dan stream
////                List<OrderDetailResponse> orderDetailResponses = orders.stream()
////                        .map(OrderDetail -> modelMapper.map(OrderDetail, OrderDetailResponse.class))
////                        .collect(Collectors.toList());
////                //setData
////                getUserOrderResponse.setData(orderDetailResponses);
////            }
////            log.info("dapat alhamdulillah {}, {}", response.getUsername(), response.getIdOrder());
////            return getUserOrderResponse;
//            log.info("wkwk ga dapat");
//            throw new DataNotFound();
//        }
//        else {
//            //konversi isi List UserOrderResponse menjadi GetUserOrderResponse
//            List<GetUserOrderResponse<List<OrderDetailResponse>>> getUserOrderResponses = response.stream()
//                      .map(UserOrderResponse -> modelMapper.map(UserOrderResponse, GetUserOrderResponse.class))
//                       .collect(Collectors.toList());
//
//            //konversi dto OrderDetail ke Order Detail Response menggunakan
//            //modelmap dan stream
//              List<OrderDetailResponse> orderDetailResponses = orders.stream()
//                        .map(OrderDetail -> modelMapper.map(OrderDetail, OrderDetailResponse.class))
//                        .collect(Collectors.toList());
//            if (orders.isEmpty()){
//                //mengisisi setData di GetUserOrderResponse menjadi null
//                getUserOrderResponses.forEach(data -> data.setData(null));
//            } else {
//                getUserOrderResponses.forEach(data -> data.setData(orderDetailResponses));
//            }
//            return getUserOrderResponses;
//        }
//    }
}
