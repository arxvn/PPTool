/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arxvn.PersonalProjectTool.Services;

import com.arxvn.PersonalProjectTool.Exceptions.ExceptionObjects.EntityNotFoundException;
import com.arxvn.PersonalProjectTool.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aruns
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MongoTemplate mongoTemplate;

    public User saveUser(User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setUsername(newUser.getUsername().toLowerCase());
        return mongoTemplate.insert(newUser);
    }

    public User findByUsername(String email, Integer i) {
        Query query = query(where("username").is(email.toLowerCase()));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    public User findByUsername(String email) {
        Query query = query(where("username").is(email.toUpperCase()));
        User user = mongoTemplate.findOne(query, User.class);

        if (user == null) {
            throw new EntityNotFoundException(User.class, "username", email);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username, 1);

        if (user == null) {
            new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Transactional
    public User loadUserById(String id) {
        Query query = query(where("id").is(id));
        User user = mongoTemplate.findOne(query, User.class);

        if (user == null) {
            new UsernameNotFoundException("User not found");
        }

        return user;
    }

}
