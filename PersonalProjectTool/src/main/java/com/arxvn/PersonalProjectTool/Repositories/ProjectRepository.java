/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Repositories;

import com.arxvn.PersonalProjectTool.Models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aruns
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    Project findByProjectIdentifier(String projectIdentifier);

}
