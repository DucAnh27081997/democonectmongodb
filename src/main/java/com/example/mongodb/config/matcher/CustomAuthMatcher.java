package com.example.mongodb.config.matcher;


import org.springframework.http.HttpHeaders;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class CustomAuthMatcher implements RequestMatcher {
    @Override
    public boolean matches(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION) != null;
        //&& request.getHeader(CUSTOM_AUTHORIZATION) != null;
    }

}
