package com.example.mongodb.controller;

import com.example.mongodb.dto.auth.LoginRequest;
import com.example.mongodb.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JWTService jwtService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        if(request.getUsername()==null || request.getPassword()==null){
            return ResponseEntity.ok("Loi");
        }
        return ResponseEntity.ok(jwtService.generateJwtToken(request.getUsername(), Map.of("date","date")));
    }
}
