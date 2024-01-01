package com.example.mongodb.controller;

import com.example.mongodb.model.GroupQuestion;
import com.example.mongodb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/question")

public class QuestionController {
    //private Logger log = Logger.getLogger(QuestionController.class.getName());

    @Autowired
    QuestionService questionService;

    @PostMapping("/add")
    public GroupQuestion add(@RequestBody GroupQuestion request) {
        try {
            return questionService.add(request);
        } catch (Exception e) {
            //log.log(e);
        }
        return null;
    }

    @PostMapping("/find-by-id")
    public GroupQuestion finById(@RequestParam String id) {
        try {
            return questionService.findById(id);
        } catch (Exception e) {
            //log.error(e);
        }
        return null;
    }


    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }
}
