/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Models;

import com.arxvn.PersonalProjectTool.Validators.UniqueProjectIdentifier;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author aruns
 */
@Document(collection = "Projects")
public class Project {

    @Id
    private String id;
    @NotBlank(message = "Project name is required")
    private String projectName;

    @Indexed(unique = true)
    @UniqueProjectIdentifier
    @Size(min = 4, max = 5, message = "Please use 4-5 characters")
    @NotBlank(message = "Project Identifier is required")
    private String projectIdentifier;
    @NotBlank(message = "Project description is required.")
    private String description;

    private Date start_date;
    private Date end_date;
    private Date created_at;
    private Date lastUpdated_at;

    public Project() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

}
