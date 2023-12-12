package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Document("TOEIC_TEST")
public class ToeicTest extends Entity implements Serializable {
    @Id
    private String testId;
    private String name;
    private String linkMP3;
    private String url;
    private Timestamp dateStart;
}
