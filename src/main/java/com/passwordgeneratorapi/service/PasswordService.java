package com.passwordgeneratorapi.service;

import com.passwordgeneratorapi.domain.password.Password;
import com.passwordgeneratorapi.domain.password.PasswordRequestDTO;
import com.passwordgeneratorapi.domain.password.PasswordResponseDTO;
import com.passwordgeneratorapi.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository repository;

    public String generatePassword(PasswordRequestDTO data) {
        String randomPassword = this.generateRandomPassword(data.useUpperCaseLetters(), data.useLowerCaseLetters(),
                data.useNumbers(), data.useSpecialCharacters(), data.passwordLength());

        Date now = new Date();
        Password newPassword = new Password();
        newPassword.setPassword(randomPassword);
        newPassword.setCreatedAt(now);

        repository.save(newPassword);
        return randomPassword;
    }

    public PasswordResponseDTO.ListPasswordPaginatedResponseDTO listPasswords(int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Password> passwordsPage = this.repository.findAll(pageable);

        List<PasswordResponseDTO> passwords = passwordsPage.map(password -> new PasswordResponseDTO(
                password.getId(),
                password.getPassword(),
                password.getCreatedAt())).stream().toList();

        return PasswordResponseDTO.listPasswordPaginated(passwords, passwordsPage.getTotalElements());
    }

    private String generateRandomPassword(Boolean useUpperCaseLetters, Boolean useLowerCaseLetters, Boolean useNumbers,
                             Boolean useSpecialCharacters, Integer passwordLength) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=[{]}?/;.^~`´'|";

        StringBuilder allowedCharacters = new StringBuilder();
        List<Character> passwordChars = new ArrayList<>();
        SecureRandom random = new SecureRandom();

        if (useUpperCaseLetters) {
            allowedCharacters.append(upperCaseLetters);
            passwordChars.add(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        }
        if (useLowerCaseLetters) {
            allowedCharacters.append(lowerCaseLetters);
            passwordChars.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        }
        if (useNumbers) {
            allowedCharacters.append(numbers);
            passwordChars.add(numbers.charAt(random.nextInt(numbers.length())));
        }
        if (useSpecialCharacters) {
            allowedCharacters.append(specialCharacters);
            passwordChars.add(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
        }

        if (allowedCharacters.isEmpty()) {
            throw new IllegalArgumentException("É necessário escolher pelo menos um tipo de caractere.");
        }

        while (passwordChars.size() < passwordLength) {
            int index = random.nextInt(allowedCharacters.length());
            passwordChars.add(allowedCharacters.charAt(index));
        }

        Collections.shuffle(passwordChars, random);

        StringBuilder password = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordChars.size(); i++) {
            password.append(passwordChars.get(i));
        }

        return password.toString();
    }
}
