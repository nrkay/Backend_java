package com.example.revisichallange3.modalMapper;

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