package com.gateway_service.gateway_service.dto;

import lombok.Data;

@Data
public class ApiResponseDto {
    private Integer status;
    private String data;
    private String message;
}
