/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Validators;

import com.arxvn.PersonalProjectTool.Repositories.ProjectRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aruns
 */
class UniqueProjectIdentifierValidator implements ConstraintValidator<UniqueProjectIdentifier, String> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void initialize(UniqueProjectIdentifier constraint) {
    }

    public boolean isValid(String projectIdentifier, ConstraintValidatorContext context) {
        return projectIdentifier != null && projectRepository.findOneByProjectIdentifier(projectIdentifier).isEmpty();
    }
}
