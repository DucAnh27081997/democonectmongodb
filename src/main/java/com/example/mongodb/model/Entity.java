package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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

    public void createTime(){
        Date now = new Date();
        this.setUpdatedAt(now);
        this.setCreateAt(now);
    }
}
