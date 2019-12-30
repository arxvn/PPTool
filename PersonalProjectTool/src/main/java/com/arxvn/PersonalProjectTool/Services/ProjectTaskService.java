/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import com.arxvn.PersonalProjectTool.Models.Backlog;
import com.arxvn.PersonalProjectTool.Models.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author aruns
 */

@Service
public class ProjectTaskService {
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private BacklogService backlogService;
    
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
    
    Backlog backlog = backlogService.findByProjectIdentifier(projectIdentifier);
    
    projectTask.setBacklogID(backlog.getId());
    
    Integer backlogSequence = backlog.getPtSequence();
    backlogSequence++;
    projectTask.setProjectSequence(backlog.getProjectIdentifier()+"-"+backlogSequence.toString());
    projectTask.setProjectIdentifier(backlog.getProjectIdentifier());
    if (projectTask.getPriority() == 0 || projectTask.getPriority() == null){
        projectTask.setPriority(3);
    }
    
    if (projectTask.getStatus() == "" || projectTask.getStatus() == null){
        projectTask.setStatus("TO_DO");
    }
    return mongoTemplate.save(projectTask);
    
    
}
}