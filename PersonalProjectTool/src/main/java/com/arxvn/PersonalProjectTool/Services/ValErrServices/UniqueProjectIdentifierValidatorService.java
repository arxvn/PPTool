/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services.ValErrServices;

import com.arxvn.PersonalProjectTool.Services.ProjectService;
import com.arxvn.PersonalProjectTool.Validators.UniqueProjectIdentifier;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aruns
 */
@Service
public class UniqueProjectIdentifierValidatorService implements ConstraintValidator<UniqueProjectIdentifier, String> {

    @Autowired
    private ProjectService projectService;

    @Override
    public void initialize(UniqueProjectIdentifier constraint) {
    }

    public boolean isValid(String projectIdentifier, ConstraintValidatorContext context) {

        if (projectIdentifier != null) {
            projectIdentifier = projectIdentifier.toUpperCase();
        }
        return projectIdentifier != null && projectService.findByProjectIdentifier(projectIdentifier, 1) == null;
    }
}
