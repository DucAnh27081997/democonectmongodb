package com.example.mongodb.controller;

import com.example.mongodb.dto.RequestDTO;
import com.example.mongodb.dto.ResponseBuilderDTO;
import com.example.mongodb.dto.ResponseDTO;
import com.example.mongodb.dto.ToeicTestParam;
import com.example.mongodb.model.ToeicTest;
import com.example.mongodb.service.ToeicTestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/api/test")
public class ToeicTestController {
    @Autowired
    ToeicTestService testService;

    @Autowired
    ResponseBuilderDTO responseBuilderDTO;

    @PostMapping("/find")
    public ResponseDTO<Page<ToeicTest>> find(@RequestBody  RequestDTO<ToeicTestParam> request){
        try {
            ToeicTestParam param = request.getParam();
            var result = testService.find(request.getPage(),request.getSorting());
            return responseBuilderDTO.success(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseBuilderDTO.exception(String.format(e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseDTO<ToeicTest> add(@RequestBody  RequestDTO<ToeicTestParam> request){
        try {
            ToeicTestParam param = request.getParam();
            var result = testService.add(param.getToeicTest());
            if(request == null){
                responseBuilderDTO.error("add test fail!");
            }
            return responseBuilderDTO.success(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseBuilderDTO.exception(String.format(e.getMessage()));
        }
    }
}
