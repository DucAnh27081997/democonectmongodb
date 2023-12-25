package com.example.mongodb.model;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER")
    ;

    Role(String value) {
        this.value = value;
    }
    private String value;
}
