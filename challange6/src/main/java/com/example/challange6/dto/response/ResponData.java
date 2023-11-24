package com.example.challange6.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponData<T> {
    private String status;
    private String message;
    private T data;
}
