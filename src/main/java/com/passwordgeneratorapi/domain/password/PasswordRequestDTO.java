package com.passwordgeneratorapi.domain.password;

public record PasswordRequestDTO(Boolean useUpperCaseLetters, Boolean useLowerCaseLetters, Boolean useNumbers,
                                 Boolean useSpecialCharacters, Integer passwordLength) {
}
