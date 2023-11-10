package com.example.challange5.model.Respon;

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
