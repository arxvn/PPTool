/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 *
 * @author aruns
 */
@Document(collection = "ProjectTasks")
@JsonInclude(Include.NON_NULL)
public class ProjectTask {

    @Id
    private String id;
    private String projectSequence;

    @NotBlank(message = "Please include a project summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    private Date created_at;
    private Date lastUpdated_at;

    private String projectIdentifier;

    @Field(targetType = FieldType.OBJECT_ID)
    private String backlogID;

    public ProjectTask() {
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getLastUpdated_at() {
        return lastUpdated_at;
    }

    public void setLastUpdated_at(Date lastUpdated_at) {
        this.lastUpdated_at = lastUpdated_at;
    }

    public String getBacklogID() {
        return backlogID;
    }

    public void setBacklogID(String backlogID) {
        this.backlogID = backlogID;
    }

    @Override
    public String toString() {
        return "ProjectTask{" + "id=" + id + ", projectSequence=" + projectSequence + ", summary=" + summary + ", acceptanceCriteria=" + acceptanceCriteria + ", status=" + status + ", priority=" + priority + ", dueDate=" + dueDate + ", created_at=" + created_at + ", lastUpdated_at=" + lastUpdated_at + '}';
    }

}
