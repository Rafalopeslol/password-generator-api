package com.passwordgeneratorapi.domain.password;

import java.util.List;

public record ListPasswordPaginatedResponseDTO(List<PasswordResponseDTO> passwords, long count) {
}
