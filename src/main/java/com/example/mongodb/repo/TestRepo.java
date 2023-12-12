package com.example.mongodb.repo;

import com.example.mongodb.model.ToeicTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends MongoRepository<ToeicTest,String> {
}
