package com.challenge_8.challenge_8.dto.request;

import java.util.Optional;

import lombok.Data;

@Data
public class UpdateUserDto {
    private Optional<String> username;

    private Optional<String> emailAddress;

    private Optional<String> password;

}
