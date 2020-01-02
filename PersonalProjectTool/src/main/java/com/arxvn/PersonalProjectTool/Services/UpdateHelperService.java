/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import org.bson.Document;
import org.springframework.data.mongodb.core.query.Update;

/**
 *
 * @author aruns
 */
public class UpdateHelperService {

    public static Update fromDocExcludeNullFields(Document object) {
        Update update = new Update();
        for (String key : object.keySet()) {
            Object value = object.get(key);
            if (value != null) {
                update.set(key, value);
            }
        }
        return update;
    }

}
