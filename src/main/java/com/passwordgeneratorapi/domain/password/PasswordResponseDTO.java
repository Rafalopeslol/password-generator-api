package com.passwordgeneratorapi.domain.password;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record PasswordResponseDTO(UUID id, String password, Date createdAt) {
    public static PasswordResponseDTO password(String password) {
        return new PasswordResponseDTO(null, password,null);
    }

    public static record ListPasswordPaginatedResponseDTO(List<PasswordResponseDTO> passwords, long count){}

    public static ListPasswordPaginatedResponseDTO listPasswordPaginated(List<PasswordResponseDTO>passwords, long count) {
        return new ListPasswordPaginatedResponseDTO(passwords, count);
    }
}
