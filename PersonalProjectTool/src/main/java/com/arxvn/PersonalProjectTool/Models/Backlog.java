/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 *
 * @author aruns
 */
@Document(collection = "Backlogs")
public class Backlog {

    @Id
    private String id;
    private Integer ptSequence = 0;
    private String projectIdentifier;

    @Field(targetType = FieldType.OBJECT_ID)
    private List<String> projectTaskIDs = new ArrayList<>();
    
    public Backlog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPtSequence() {
        return ptSequence;
    }

    public void setPtSequence(Integer ptSequence) {
        this.ptSequence = ptSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public List<String> getProjectTaskIDs() {
        return projectTaskIDs;
    }

    public void setProjectTaskIDs(List<String> projectTaskIDs) {
        this.projectTaskIDs = projectTaskIDs;
    }

    @Override
    public String toString() {
        return "Backlog{" + "id=" + id + ", ptSequence=" + ptSequence + ", projectIdentifier=" + projectIdentifier + ", projectTaskIDs=" + projectTaskIDs + '}';
    }

    
    
}
