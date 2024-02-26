package com.challenge_8.challenge_8.dto.response;

import io.micrometer.common.lang.NonNull;
import lombok.Data;

@Data
public class UserInfoDto {
    @NonNull
    private String sub;

    @NonNull
    private String name;

    @NonNull
    private String given_name;

    @NonNull
    private String family_name;

    @NonNull
    private String picture;

    @NonNull
    private String email;

    @NonNull
    private boolean email_verified;

    @NonNull
    private String locale;

    @NonNull
    private String error;

    @NonNull
    private String error_description;
}
