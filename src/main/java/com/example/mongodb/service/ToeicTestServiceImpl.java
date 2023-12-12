package com.example.mongodb.service;

import com.example.mongodb.dto.PageDTO;
import com.example.mongodb.dto.SortDTO;
import com.example.mongodb.model.ToeicTest;
import com.example.mongodb.repo.TestRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ToeicTestServiceImpl implements ToeicTestService {

    @Autowired
    TestRepo testRepo;

    @Override
    public Page<ToeicTest> find(PageDTO page, SortDTO sort) {
        Sort sorting;
        if (sort.isEmpty()) {
            sorting = null;
        } else sorting = Sort.by(sort.getDirection(), sort.getBy());

        Pageable paging;
        if (page == null) {
            paging = PageRequest.of(0, 10, sorting);
        } else paging = PageRequest.of(page.getPageNo(), page.getPageSize(), sorting);
        return testRepo.findAll(paging);
    }

    @Override
    public ToeicTest add(ToeicTest toeicTest) {
        return testRepo.save(toeicTest);
    }

    @Override
    public ToeicTest update(ToeicTest toeicTest) {
        if (toeicTest.getTestId() == null) {
            toeicTest.setTestId(new ObjectId().toString());
            toeicTest.setUpdatedAt(new Date());
        }
        return testRepo.save(toeicTest);
    }

    @Override
    public ToeicTest findById(String Id) {
        return testRepo.findById(Id).orElse(null);
    }

    @Override
    public boolean delete(String Id) throws Exception {
        try {
            var result = testRepo.findById(Id);
            if(result.isEmpty()){
                return false;
            }else {
                testRepo.deleteById(Id);
                return true;
            }
        }catch (Exception e){
            throw new Exception("delete test error");
        }
    }
}
