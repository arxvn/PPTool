/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects.EntityNotFoundException;
import com.arxvn.PersonalProjectTool.Models.Backlog;
import com.arxvn.PersonalProjectTool.Models.Project;
import java.util.Date;
import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 *
 * @author aruns
 */
@Service
public class ProjectService {

    @Autowired
    MongoTemplate mongoTemplate;
    
    @Autowired
    ProjectTaskService projectTaskService;
    
    @Autowired
    BacklogService backlogService;

    public ProjectService() {
    }

    public Project saveProject(Project project) {
        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        Backlog backlog = new Backlog();
        backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        backlog = mongoTemplate.insert(backlog);
        project.setBacklogID(backlog.getId());
        return mongoTemplate.insert(project);
    }

    public Project findByProjectIdentifier(String id, Integer i) {
        Query query = query(where("projectIdentifier").is(id.toUpperCase()));
        Project project = mongoTemplate.findOne(query, Project.class);
        return project;
    }

    public Project findByProjectIdentifier(String id) {
        Query query = query(where("projectIdentifier").is(id.toUpperCase()));
        Project project = mongoTemplate.findOne(query, Project.class);
        if (project == null) {
            throw new EntityNotFoundException(Project.class, "projectIed", id);
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        
        return mongoTemplate.findAll(Project.class);
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = findByProjectIdentifier(projectId);
        mongoTemplate.remove(query(where("projectIdentifier").is(projectId.toUpperCase())), Project.class);
        projectTaskService.deleteProjectTasksByIdentifier(projectId);
        backlogService.deleteByProjectIdentifier(projectId);
    }

    public Project updateProject(Project project) {
        Document doc = new Document();
        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        project.setLastUpdated_at(new Date());
        mongoTemplate.getConverter().write(project, doc);
        Update update = UpdateHelperService.fromDocExcludeNullFields(doc);
        Project p = mongoTemplate.findAndModify(query(where("projectIdentifier")
                .is(project.getProjectIdentifier().toUpperCase())), update,
                FindAndModifyOptions.options().returnNew(true), Project.class);
        if (p == null) {
            throw new EntityNotFoundException(Project.class, "projectId", project.getProjectIdentifier());
        }
        return p;

    }

}
