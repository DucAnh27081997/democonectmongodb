package com.example.mongodb.model;

//import jakarta.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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

    @CreatedDate
    private Date createAt;

    //    @PrePersist
    @LastModifiedDate
    protected void onCreate() {
        createAt = new Date();
    }
}
