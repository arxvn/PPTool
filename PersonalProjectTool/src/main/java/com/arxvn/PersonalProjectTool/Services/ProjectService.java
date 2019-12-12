/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects.EntityNotFoundException;
import com.arxvn.PersonalProjectTool.Models.Project;
import com.arxvn.PersonalProjectTool.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aruns
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        return projectRepository.save(project);
    }

    public Project findById(String id) {
        Project project = projectRepository.findByProjectIdentifier(id.toUpperCase());
        if (project == null) {
            throw new EntityNotFoundException(Project.class, "Project Identifier", id.toString());
        }
        return project;
    }
    
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
}
