package com.example.revisichallange3.service;

import com.example.revisichallange3.Advice.handleException.DataNotFoundException;
import com.example.revisichallange3.dto.user.UserRequest;
import com.example.revisichallange3.dto.user.UserResponse;
import com.example.revisichallange3.model.User;
import com.example.revisichallange3.repository.UsersRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {
    @Mock
    private UsersRepository usersRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void findById_userFound() {
        UUID userId = UUID.randomUUID();
        User mockUser = new User();
        mockUser.setId(userId);

        given(usersRepository.findById(userId)).willReturn(Optional.of(mockUser));

        User result = userService.findById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    void findById_userNotFound() {
        UUID nonExistentUserId = UUID.randomUUID();

        given(usersRepository.findById(nonExistentUserId)).willReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> userService.findById(nonExistentUserId));
    }


    @Test
    void deleteUser() {
        UUID userId = UUID.randomUUID();
        User existingUser = new User();
        existingUser.setId(userId);

        given(usersRepository.findById(userId)).willReturn(Optional.of(existingUser));

        userService.delete(userId);

        verify(usersRepository).delete(existingUser);
    }

}

