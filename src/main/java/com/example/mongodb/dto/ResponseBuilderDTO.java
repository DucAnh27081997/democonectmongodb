package com.example.mongodb.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseBuilderDTO {
    public ResponseBuilderDTO() {

    }

    public <T> ResponseDTO<T> success(T body) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setStatus(Status.SUCCESS);
        response.setMessage("");
        response.setBody(body);
        return response;
    }

    public <T> ResponseDTO<T> success(T body,String message) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setStatus(Status.SUCCESS);
        response.setMessage(message);
        response.setBody(body);
        return response;
    }

    public ResponseDTO success(String message) {
        ResponseDTO response = new ResponseDTO<>();
        response.setStatus(Status.SUCCESS);
        response.setMessage(message);
        return response;
    }

    public ResponseDTO error(String message) {
        ResponseDTO response = new ResponseDTO<>();
        response.setStatus(Status.ERROR);
        response.setMessage(message);
        return response;
    }
    public ResponseDTO exception(String message) {
        ResponseDTO response = new ResponseDTO<>();
        response.setStatus(Status.EXCEPTION);
        response.setMessage(message);
        return response;
    }


}
