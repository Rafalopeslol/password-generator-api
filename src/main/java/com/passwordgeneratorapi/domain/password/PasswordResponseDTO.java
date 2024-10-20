package com.passwordgeneratorapi.domain.password;

import java.util.Date;
import java.util.UUID;

public record PasswordResponseDTO(UUID id, String password, Date createdAt) {
    public static PasswordResponseDTO password(String password) {
        return new PasswordResponseDTO(null, password,null);
    }
}
