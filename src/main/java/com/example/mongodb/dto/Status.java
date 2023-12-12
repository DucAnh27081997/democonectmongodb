package com.example.mongodb.dto;

public enum Status {
    SUCCESS("success"),
    ERROR("error"),
    EXCEPTION("exception");
    private String value;

    Status(String value) {
        this.value = value;
    }
}
