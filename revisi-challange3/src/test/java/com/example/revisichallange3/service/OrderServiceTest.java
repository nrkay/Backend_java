package com.example.revisichallange3.service;

import com.example.revisichallange3.model.Orders;
import com.example.revisichallange3.model.User;
import com.example.revisichallange3.repository.OrdersRepository;
import com.example.revisichallange3.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
    void createOrder_userNotFound() {
        UUID nonExistentUserId = UUID.randomUUID();
        Orders request = new Orders();
        request.setLocation("Test Location");

        given(usersRepository.findById(nonExistentUserId)).willReturn(Optional.empty());

        Orders result = orderService.create(nonExistentUserId, request);

        assertNull(result);
    }

    @Test
    void createOrder_userDeleted() {
        UUID deletedUserId = UUID.randomUUID();
        Orders request = new Orders();
        request.setLocation("Test Location");

        User deletedUser = new User();
        deletedUser.setId(deletedUserId);
        deletedUser.setDeleted(true);

        given(usersRepository.findById(deletedUserId)).willReturn(Optional.of(deletedUser));

        Orders result = orderService.create(deletedUserId, request);

        assertNull(result);
    }

    @Test
    void removeOrder_orderFoundAndNotDeleted() {
        UUID orderId = UUID.randomUUID();
        Orders existingOrder = new Orders();
        existingOrder.setId(orderId);
        existingOrder.setDeleted(false);

        given(ordersRepository.findById(orderId)).willReturn(Optional.of(existingOrder));

        Orders result = orderService.remove(orderId);

        verify(ordersRepository).deleteById(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
    }


    @Test
    void updateOrder_orderFoundAndNotDeleted() {
        UUID orderId = UUID.randomUUID();
        Orders existingOrder = new Orders();
        existingOrder.setId(orderId);
        existingOrder.setDeleted(false);

        Orders updatedOrderRequest = new Orders();
        updatedOrderRequest.setLocation("Updated Location");

        given(ordersRepository.findById(orderId)).willReturn(Optional.of(existingOrder));
        given(ordersRepository.save(existingOrder)).willReturn(existingOrder);

        Orders result = orderService.update(orderId, updatedOrderRequest);

        assertNotNull(result);
        assertEquals("Updated Location", result.getLocation());
    }

}
