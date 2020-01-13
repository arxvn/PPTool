/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Listeners;

import com.arxvn.PersonalProjectTool.Models.User;
import java.util.Date;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

/**
 *
 * @author aruns
 */
@Configuration
public class UserListener extends AbstractMongoEventListener<User> {
        @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {

        super.onBeforeConvert(event);
        User entity = event.getSource();
        Date d = new Date();

        if (entity.getCreated_at() == null) {
            entity.setCreated_at(d);
        }

        entity.setLastUpdated_at(d);
    }
}
