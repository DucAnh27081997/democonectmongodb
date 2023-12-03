package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
//    @NotEmpty
    private String userId;
//    @NotEmpty
    private String createdBy;
    private Date updatedAt;
    private Date createAt;
}
