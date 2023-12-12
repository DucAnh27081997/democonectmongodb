package com.example.mongodb.controller;

import com.example.mongodb.dto.RequestDTO;
import com.example.mongodb.dto.ResponseBuilderDTO;
import com.example.mongodb.dto.ResponseDTO;
import com.example.mongodb.dto.ToeicTestParam;
import com.example.mongodb.exception.ExceptionHandlerController;
import com.example.mongodb.model.ToeicTest;
import com.example.mongodb.service.ToeicTestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/api/test")
//@ExceptionHandler(value = {"ExceptionHandlerController.class"})
public class ToeicTestController {
    @Autowired
    ToeicTestService testService;

    @Autowired
    ResponseBuilderDTO responseBuilderDTO;

    @PostMapping("/find")
    public ResponseDTO<Page<ToeicTest>> find(@RequestBody RequestDTO<ToeicTest> request) {
        try {
            ToeicTest param = request.getParam();
            if (param == null) {
                return responseBuilderDTO.error("param is null!");
            }
            var result = testService.find(request.getPage(), request.getSorting());
            return responseBuilderDTO.success(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseBuilderDTO.exception(String.format(e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseDTO<ToeicTest> add(@RequestBody RequestDTO<ToeicTest> request) {
        try {
            ToeicTest param = request.getParam();
            var result = testService.add(param);
            if (request == null) {
                return responseBuilderDTO.error("param is null!");
            }
            return responseBuilderDTO.success(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseBuilderDTO.exception(String.format(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseDTO<ToeicTest> update(@RequestBody RequestDTO<ToeicTest> request) {
        try {
            ToeicTest param = request.getParam();
            var result = testService.update(param);
            if (request == null) {
                return responseBuilderDTO.error("param is null!");
            }
            return responseBuilderDTO.success(result, "update success!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseBuilderDTO.exception(String.format(e.getMessage()));
        }
    }

    @GetMapping("/update")
    public ResponseDTO<ToeicTest> update(@RequestParam String id) {
        try {
            if (id == null) {
                return responseBuilderDTO.error("id is not null or empty!");
            }
            ToeicTest result = testService.findById(id);
            if (result == null) {
                responseBuilderDTO.error(String.format("not found test have id \'%s\' !", id));
            }
            return responseBuilderDTO.success(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseBuilderDTO.exception(String.format(e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseDTO<String> delete(@RequestParam String id) {
        try {
            if (id == null) {
                return responseBuilderDTO.error("id is not null or empty!");
            }
            boolean result = testService.delete(id);
            if(!result){
                return responseBuilderDTO.error(String.format("not found test have id \'%s\' !", id));
            }
            return responseBuilderDTO.success("Delete success!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return responseBuilderDTO.exception(String.format(e.getMessage()));
        }
    }
}
