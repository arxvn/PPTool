/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Validators;

import com.arxvn.PersonalProjectTool.Services.ValErrServices.UniqueProjectIdentifierValidatorService;
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
@Constraint(validatedBy = UniqueProjectIdentifierValidatorService.class)
public @interface UniqueProjectIdentifier {
    String message() default "A identifier already exists of that name.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
