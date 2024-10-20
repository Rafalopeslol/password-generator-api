package com.passwordgeneratorapi.domain.password;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(schema = "password", name = "passwords")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Password {
    @Id
    @GeneratedValue
    private UUID id;
    private String password;
    private Date createdAt;
}
