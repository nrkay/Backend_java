package com.challenge_8.challenge_8.dto.response;

import java.util.Collection;
import java.util.UUID;

import com.challenge_8.challenge_8.entity.Role;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
class Merchant {
    private UUID id;
    private String merchantName;
    private String merchantLocation;
}

@Data
public class UserDto {
    @Id
    private UUID id;

    @NotNull
    private String username;

    @NotNull
    private String emailAddress;

    private Merchant merchant;

    private Collection<Role> roles;
}
