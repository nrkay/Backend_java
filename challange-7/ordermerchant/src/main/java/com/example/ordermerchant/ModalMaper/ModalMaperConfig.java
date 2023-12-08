package com.example.ordermerchant.ModalMaper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModalMaperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
