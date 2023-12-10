package com.khairiyah.revisichallange4.dto.user;
import lombok.*;

import java.util.UUID;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String username;
    private String password;
}
