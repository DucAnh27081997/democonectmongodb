package com.example.mongodb.dto;

import lombok.Data;

@Data
public class RequestDTO<T> {
    private T param;
    private SortDTO sorting;
    private  PageDTO page;
}
