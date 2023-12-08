package com.example.ordermerchant.client;

import com.example.ordermerchant.dto.Product.OrderCompleted;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "order-service", url = "http://localhost:8222/api/v1/order/orderDetail")
public interface OrderCompletedClient {
    @GetMapping("/orderCompleted/{id_product}")
    List<OrderCompleted> orderCompletedByProduct(@PathVariable("id_product") UUID id_product);


}
