package com.example.challange5.exception.DeleteSuccess;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeleteSuccess {

    public static ResponseEntity<Map<String, String>> deleteSuccess(){
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("Message", "Delete success");
        return ResponseEntity.ok(responseMessage);
    }
}
