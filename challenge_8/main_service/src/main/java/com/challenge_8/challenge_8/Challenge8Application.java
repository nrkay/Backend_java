package com.challenge_8.challenge_8;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.challenge_8.challenge_8.dto.response.OrderDetailDto;
import com.challenge_8.challenge_8.dto.response.OrderDto;
import com.challenge_8.challenge_8.entity.Order;
import com.challenge_8.challenge_8.entity.OrderDetail;

@SpringBootApplication
public class Challenge8Application {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<List<OrderDetail>, List<OrderDetailDto>> convertOrderDetail = new AbstractConverter<>() {
            protected List<OrderDetailDto> convert(List<OrderDetail> source) {
                return source.stream().map(orderDetail -> {
                    OrderDetailDto orderDetailDto = modelMapper.map(orderDetail, OrderDetailDto.class);
                    orderDetailDto.setPrice(orderDetail.getTotalPrice() / orderDetail.getQuantity());
                    return orderDetailDto;
                }).collect(Collectors.toList());
            }
        };
        modelMapper.typeMap(Order.class, OrderDto.class)
                .addMappings(mapper -> mapper.using(convertOrderDetail)
                        .map(Order::getOrderDetails, OrderDto::setOrderDetails));

        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(Challenge8Application.class, args);
    }

}
