package com.example.mongodb.dto;

import com.example.mongodb.model.ToeicTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToeicTestParam {
    private ToeicTest toeicTest;
}
