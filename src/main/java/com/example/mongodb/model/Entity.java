package com.example.mongodb.model;

//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

//    @PrePersist
    protected void onCreate(){
        createAt = new Date();
    }

//    @PreUpdate
    public void preUpdate() {
        System.out.println("pre update!");
        updatedAt = new Date();
    }
}
