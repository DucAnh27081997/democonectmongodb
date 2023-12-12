package com.example.mongodb.service;

import com.example.mongodb.dto.PageDTO;
import com.example.mongodb.dto.SortDTO;
import com.example.mongodb.model.ToeicTest;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ToeicTestService {
    public Page<ToeicTest> find(PageDTO page, SortDTO sort);
    public ToeicTest add(ToeicTest toeicTest);
    public ToeicTest update(ToeicTest toeicTest);
    public ToeicTest findById(String Id);
    public boolean delete(String Id) throws Exception;
}
