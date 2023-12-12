package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {
    private int index;
    private String context;
    private String linkImage;
    private String linkMp3;
    private String A,B,C,D;
    private String explain;
}
