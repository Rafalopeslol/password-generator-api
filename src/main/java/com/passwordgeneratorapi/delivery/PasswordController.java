package com.passwordgeneratorapi.delivery;

import com.passwordgeneratorapi.domain.password.PasswordRequestDTO;
import com.passwordgeneratorapi.domain.password.PasswordResponseDTO;
import com.passwordgeneratorapi.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @RequestMapping("/api/generate-password")
    @PostMapping
    public ResponseEntity<PasswordResponseDTO> create(@RequestBody PasswordRequestDTO body) {
        if (body.passwordLength() == null || body.passwordLength() < 4) {
            throw new IllegalArgumentException("O número de caracteres da senha precisa ser pelo menos quatro.");
        }

        if (body.passwordLength() > 100) {
            throw new IllegalArgumentException("O número de caracteres da senha não pode ser maior que cem.");
        }

        String newPassword = this.passwordService.generatePassword(body);
        return ResponseEntity.ok(PasswordResponseDTO.password(newPassword));
    }

    @RequestMapping("/api/password-history")
    @GetMapping
    public ResponseEntity<PasswordResponseDTO.ListPasswordPaginatedResponseDTO> listPasswords(@RequestParam() int page){
        PasswordResponseDTO.ListPasswordPaginatedResponseDTO response = this.passwordService.listPasswords(page);

        return ResponseEntity.ok(response);
    }
}
