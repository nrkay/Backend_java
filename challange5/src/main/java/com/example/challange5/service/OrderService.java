package com.example.challange5.service;


import com.example.challange5.model.CustomerOrder;
import com.example.challange5.model.CustomerUser;
import com.example.challange5.model.OrderDetail;
import com.example.challange5.model.dto.CustomerOrderResponse;
import com.example.challange5.model.dto.OrderDetailResponse;
import com.example.challange5.repository.CustomerOrderRepository;
import com.example.challange5.repository.CustomerUserRepository;
import com.example.challange5.repository.OrderDetailRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerUserRepository customerUserRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerOrder create(UUID id, CustomerOrder request){
        Optional<CustomerUser> user = customerUserRepository.findById(id);
        if (user.isPresent()){
            if (user.get().getDeleted().equals(false)){
                CustomerOrder orderCreate = CustomerOrder.builder()
                        .completed(false)
                        .user(user.get())
                        .orderTime(LocalDate.now())
                        .location(request.getLocation())
                        .build();
                log.info("order create : {}", orderCreate);
                return customerOrderRepository.save(orderCreate);
            } else {
                log.info("user was deleted");
                return null;
            }
        } else {
            log.info("user not found");
            return null;
        }
    }

    public CustomerOrder remove(UUID id){
        Optional<CustomerOrder> order = customerOrderRepository.findById(id);
        if (order.isPresent()){
            if (order.get().getDeleted().equals(false)){
                CustomerOrder customerOrderRespon = order.get();
                customerOrderRepository.deleteById(id);
                log.info("Delete Success");
                return customerOrderRespon;
            } else {
                log.info("Data was Deleted");
                return null;
            }
        } else {
            log.info("Data not found");
            return null;
        }
    }

    public CustomerOrder update(UUID id, CustomerOrder request){
        Optional<CustomerOrder> order = customerOrderRepository.findById(id);
        if (order.isPresent()){
            if (order.get().getDeleted().equals(false)){
                order.get().setLocation(request.getLocation());
                log.info("update order: {}", order);
                return customerOrderRepository.save(order.get());
            } else {
                log.info("data was deleted");
                return null;
            }
        } else {
            log.info("data not found");
            return null;
        }
    }

    //chackout order
    @Transactional
    public CustomerOrderResponse<OrderDetailResponse> checkout(UUID id){
        //cek order
        //dicek, ada ga product order yg di add
        //kalau ada, laksanakan :
        //ubah status order
        //hitung totalPrice
        //kalau ga ada, laksanakan :
        // return null
        Optional<CustomerOrder> response = customerOrderRepository.findById(id);
        List<OrderDetail> orders = orderDetailRepository.findByCustomerOrder_Id(id);
        if (orders.isEmpty()){
            //kalau kosong
            log.info("order kosong");
            return null;
        } else {
            //menghitung jumlah semua total data dari column totalPrice
            Long totalPrice = 0L;
            for (OrderDetail orderDetail : orders) {
                totalPrice += orderDetail.getTotalPrice();
            }
            log.info("total price nya adalah : {}", totalPrice);
            //update status order dan totalPrice
            response.get().setCompleted(true);
            response.get().setTotalPrice(totalPrice);
            response.get().setComplated_at(LocalDate.now());
            customerOrderRepository.save(response.get());
            log.info("update order is succes : {}, {}", response.get().getCompleted(), response.get().getTotalPrice());

            //konversi dto untuk return
            //konversi List Order Detail ke List OrderdetailResponse
            List<OrderDetailResponse> orderDetailResponses = orders.stream()
                    .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailResponse.class))
                    .collect(Collectors.toList());
            //konversi CustomerOrder ke CustomerOrderResponse
            CustomerOrderResponse customerOrderResponse = modelMapper.map(response.get(), CustomerOrderResponse.class);
            customerOrderResponse.setData(orderDetailResponses);
            customerOrderResponse.setUsername(response.get().getUser().getUsername());
            return customerOrderResponse;
        }
    }
}
