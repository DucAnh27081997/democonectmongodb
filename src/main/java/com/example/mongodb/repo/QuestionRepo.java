package com.example.mongodb.repo;

import com.example.mongodb.model.GroupQuestion;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
@Log4j2
public class QuestionRepo {

    @Autowired
    private MongoTemplate template;

    public GroupQuestion findById(String Id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(Id));
        return template.findOne(query, GroupQuestion.class);
    }

    public List<GroupQuestion> find(String query) {
        if (query == null || query.isEmpty())
            query = "{}";
        BasicQuery basicQuery = new BasicQuery(query);
        return template.find(basicQuery, GroupQuestion.class);
    }

    public GroupQuestion save(GroupQuestion groupQuestion) throws Exception {
        try {
            if (groupQuestion.getQuestionId() == null) {
                groupQuestion.setQuestionId(new ObjectId().toString());
                groupQuestion.createTime();
                return template.insert(groupQuestion);
            } else {
                GroupQuestion entity = this.findById(groupQuestion.getQuestionId());
                if (entity == null)
                    return null;
                else {
                    entity.setQuestions(groupQuestion.getQuestions());
                    entity.setUpdatedAt(new Date());
                    entity.setCreatedBy("USER_DEFAULT");
                    entity.setTypeQuestion(groupQuestion.getTypeQuestion());
                    entity.setContext(groupQuestion.getContext());
                    return template.save(entity);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e);
        }

    }
}
