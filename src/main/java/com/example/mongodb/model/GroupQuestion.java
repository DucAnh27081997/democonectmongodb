package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Document("GROUP_QUESTION")
public class GroupQuestion extends Entity implements Serializable {
    @Id
    private String questionId;
    private String context;
    private String typeQuestion;
    private List<Question> questions;
}
