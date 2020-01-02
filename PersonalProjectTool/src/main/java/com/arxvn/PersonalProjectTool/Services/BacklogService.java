/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects.EntityNotFoundException;
import com.arxvn.PersonalProjectTool.Models.Backlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.stereotype.Service;

/**
 *
 * @author aruns
 */
@Service
public class BacklogService {

    @Autowired
    MongoTemplate mongoTemplate;

    public Backlog findByProjectIdentifier(String id) {
        Query query = query(where("projectIdentifier").is(id.toUpperCase()));
        Backlog backlog = mongoTemplate.findOne(query, Backlog.class);

        if (backlog == null) {
            throw new EntityNotFoundException(Backlog.class, "projectIdentifier", id);
        }
        return backlog;
    }

    public void deleteByProjectIdentifier(String id) {
        mongoTemplate.remove(query(where("projectIdentifier").is(id.toUpperCase())), Backlog.class);

    }
}
