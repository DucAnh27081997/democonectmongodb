package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
@Document(value = "USER")
public class User {
    @Id
    private String idUser;
    private String username;
    private String email;
    private boolean isEnabled;
    private Collection<Role> roles;
}
