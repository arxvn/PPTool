/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services.ValErrServices;

import com.arxvn.PersonalProjectTool.Services.UserService;
import com.arxvn.PersonalProjectTool.Validators.UniqueUserEmailValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aruns
 */
public class UniqueUserEmailValidatorService implements ConstraintValidator<UniqueUserEmailValidator, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueUserEmailValidator constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && userService.findByUsername(email, 1) == null;
    }

    
}
