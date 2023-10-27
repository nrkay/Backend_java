package com.example.demo.model.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProductDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private String price;

}
