package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Document(value = "TOKEN")
public class Token {
    @Id
    private String tokenId;
    private String email;
    private String token;
    private String refreshToken;
    private boolean isMobile;
    private Date expiryDate;
    private Date createAt;
}
