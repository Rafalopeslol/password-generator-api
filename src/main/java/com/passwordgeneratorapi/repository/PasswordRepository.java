package com.passwordgeneratorapi.repository;

import com.passwordgeneratorapi.domain.password.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasswordRepository extends JpaRepository <Password, UUID> {
}
