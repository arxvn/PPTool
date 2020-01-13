/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects.EntityNotFoundException;
import com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects.EntityNotMatchPathException;
import com.arxvn.PersonalProjectTool.Models.Backlog;
import com.arxvn.PersonalProjectTool.Models.Project;
import com.arxvn.PersonalProjectTool.Models.ProjectTask;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
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
public class ProjectTaskService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BacklogService backlogService;

    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        Backlog backlog = backlogService.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklogID(backlog.getId());
        Integer backlogSequence = backlog.getPtSequence();
        backlogSequence++;
        backlog.setPtSequence(backlogSequence);
        projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence.toString());
        projectTask.setProjectIdentifier(backlog.getProjectIdentifier());
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
            projectTask.setStatus("TO_DO");
        }
        projectTask.setId(new ObjectId().toString());
        backlog.getProjectTaskIDs().add(projectTask.getId());
        mongoTemplate.save(backlog);

        return mongoTemplate.insert(projectTask);
    }

    public List<ProjectTask> findByProjectIdentifierOrderByPriority(String id) {
        id = id.toUpperCase();

        Project p = projectService.findByProjectIdentifier(id);
        List<ProjectTask> projectTaskList = new ArrayList();
        MatchOperation matchStage = Aggregation.match(new Criteria("projectIdentifier").is(id));
        SortOperation sortBypriority = sort(Direction.DESC, "priority");
        ProjectionOperation projectStage = Aggregation.project("priority", "status", "summary", "projectSequence", "created_at", "lastUpdated_at", "projectIdentifier", "acceptanceCriteria");

        Aggregation aggregation = Aggregation.newAggregation(matchStage, projectStage, sortBypriority);

        AggregationResults<ProjectTask> output = mongoTemplate.aggregate(aggregation, "ProjectTasks", ProjectTask.class);
        projectTaskList = output.getMappedResults();
        return projectTaskList;

    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_sequence) {
        Backlog backlog = backlogService.findByProjectIdentifier(backlog_id.toUpperCase());
        Query query = query(where("projectSequence").is(pt_sequence.toUpperCase()));
        ProjectTask pTask = mongoTemplate.findOne(query, ProjectTask.class);

        if (pTask == null) {
            throw new EntityNotFoundException(ProjectTask.class, "projectTask_sequence", pt_sequence);
        }

        if (!pTask.getProjectIdentifier().equals(backlog_id.toUpperCase())) {
            throw new EntityNotFoundException(ProjectTask.class, "projectTask_sequence", pt_sequence);

        }
        return pTask;
    }

    public ProjectTask updateProjectTaskByProjectSequence(ProjectTask projectTask, String projectIdentifier, String pt_sequence) {
        Document doc = new Document();
        projectTask.setProjectIdentifier(projectTask.getProjectIdentifier().toUpperCase());
        projectTask.setLastUpdated_at(new Date());
        mongoTemplate.getConverter().write(projectTask, doc);
        Update update = UpdateHelperService.fromDocExcludeNullFields(doc);
        ProjectTask p = mongoTemplate.findAndModify(query(where("projectSequence")
                .is(projectTask.getProjectSequence().toUpperCase())), update,
                FindAndModifyOptions.options().returnNew(true), ProjectTask.class);
        if (p == null) {
            throw new EntityNotFoundException(Project.class, "projectId", projectTask.getProjectIdentifier());
        }

        if (!projectTask.getProjectIdentifier().equals(projectIdentifier.toUpperCase())) {
            throw new EntityNotMatchPathException(projectTask.getProjectIdentifier(), "projectIdentifier", projectIdentifier);
        }

        if (!projectTask.getProjectSequence().equals(pt_sequence.toUpperCase())) {
            throw new EntityNotMatchPathException(projectTask.getProjectSequence(), "pt_sequence", pt_sequence);

        }
        return p;
    }

    public void deleteProjectTaskBySequence(String backlog_id, String projectId) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, projectId);
        mongoTemplate.remove(query(where("projectSequence").is(projectId.toUpperCase())), ProjectTask.class);
    }

    public void deleteProjectTasksByIdentifier(String projectId) {
        mongoTemplate.remove(query(where("projectIdentifier").is(projectId.toUpperCase())), ProjectTask.class);
    }
}
