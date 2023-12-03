package com.example.mongodb.service;

import com.example.mongodb.model.GroupQuestion;
import com.example.mongodb.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    public GroupQuestion findById(String id) {
        return questionRepo.findById(id);
    }

    public GroupQuestion add(GroupQuestion groupQuestion) throws Exception {
        return questionRepo.save(groupQuestion);
    }
}
