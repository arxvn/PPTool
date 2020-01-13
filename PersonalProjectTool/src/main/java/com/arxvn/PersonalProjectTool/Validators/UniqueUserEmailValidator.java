/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Validators;

import com.arxvn.PersonalProjectTool.Services.ValErrServices.UniqueUserEmailValidatorService;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author aruns
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserEmailValidatorService.class)
public @interface UniqueUserEmailValidator {
     String message() default "A user already exists of that username.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
