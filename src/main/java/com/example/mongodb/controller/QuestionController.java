package com.example.mongodb.controller;

import com.example.mongodb.model.GroupQuestion;
import com.example.mongodb.service.QuestionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/question")
@Log4j2
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/add")
    public GroupQuestion add(@RequestBody GroupQuestion request) {
        try {
            return questionService.add(request);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }
}
