/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 *
 * @author aruns
 */
@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationService(BindingResult result) {

        if (result.hasErrors()) {

            Map<String, List<String>> errorMap = new HashMap<>();

            errorMap = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap((fieldError) -> fieldError.getField(),
                                    (fieldError) -> new ArrayList<>(Arrays.asList(fieldError.getDefaultMessage())),
                                    (l1,l2)->{l1.addAll(l2);return l1;}));
                         
                   
            return new ResponseEntity<Map<String, List<String>>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        return null;
    }

}
