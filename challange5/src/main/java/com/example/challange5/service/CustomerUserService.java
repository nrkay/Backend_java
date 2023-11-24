package com.example.challange5.service;


import com.example.challange5.exception.DataNotFound;
import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.CustomerUser;
import com.example.challange5.model.OrderDetail;
import com.example.challange5.model.dto.OrderDetailResponse;
import com.example.challange5.model.dto.OrderDto.GetUserOrderResponse;
import com.example.challange5.model.dto.OrderDto.UserOrderResponse;
import com.example.challange5.model.dto.UserDto.UserResponseDto;
import com.example.challange5.repository.CustomerOrderRepository;
import com.example.challange5.repository.CustomerUserRepository;
import com.example.challange5.repository.OrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private CustomerOrderRepository customerOrderRepository;





    //get user by id
    public UserResponseDto get(UUID id){
        Optional<CustomerUser> respon = customerUserRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                log.info("user is found : {}", respon.get());
                return modelMapper.map(respon.get(), UserResponseDto.class);
            } else {
                throw new DataNotFound();
            }
        } else {
            throw new DataNotFound();
        }
    }
    public CustomerUser getReal(UUID id){
        Optional<CustomerUser> respon = customerUserRepository.findById(id);
        if (respon.isPresent()){
            if (respon.get().getDeleted().equals(false)){
                log.info("user is found : {}", respon.get());
                return respon.get();
            } else {
                throw new DataNotFound();
            }
        } else {
            throw new DataNotFound();
        }
    }




    public UserResponseDto save(CustomerUser customerUser){
        CustomerUser user = customerUserRepository.save(customerUser);
        return modelMapper.map(user, UserResponseDto.class);
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


    public List<GetUserOrderResponse<List<OrderDetailResponse>>> getAllOrderByIdUser(UUID idUser) {
        List<UserOrderResponse> responses = customerUserRepository.findAllOrdersbyUserId(idUser);
        if (responses.isEmpty()) {
            throw new DataNotFound();
        } else {
            // Kustom mapping dari UserOrderResponse ke GetUserOrderResponse
            List<GetUserOrderResponse<List<OrderDetailResponse>>> orderDetails = responses.stream()
                    .map(userOrderResponse -> {
                        //mengubah  List<UserOrderResponse> menjadi  List<GetUserOrderResponse>
                        GetUserOrderResponse<List<OrderDetailResponse>> userOrderResponseList = modelMapper.map(userOrderResponse, GetUserOrderResponse.class);

                        // Ambil order details berdasarkan userOrderId
                        List<OrderDetail> orders = orderDetailRepository.findByCustomerOrder_Id(userOrderResponse.getIdOrder());

                        // Konversi OrderDetail ke OrderDetailResponse
                        List<OrderDetailResponse> orderDetailResponses = orders.stream()
                                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailResponse.class))
                                .collect(Collectors.toList());

                        // Set data di GetUserOrderResponse
                        userOrderResponseList.setOrderDetail(orderDetailResponses);

                        return userOrderResponseList;
                    })
                    .collect(Collectors.toList());

            return orderDetails;
        }
    }

}
//