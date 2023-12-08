package com.example.orderservice.ExceptionHandler.custome;

public class DataNotFound extends RuntimeException{
    public DataNotFound(String message) {
        super(message);
    }
}
