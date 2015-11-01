package com.review.userreview.domain.datamodel.impl;

import com.review.userreview.domain.datamodel.UserRepositoryCustom;
import com.review.userreview.domain.datamodel.dto.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * User data model repo. This class implements the database read and write operations.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDAO create(UserDAO userDAO) {
        mongoTemplate.insert(userDAO);
        return userDAO;
    }

//    @Override
//    public UserDAO getUserByFullName(String firstName, String lastName) {
//        Query searchUserQuery = new Query(Criteria.where("first_name").is(firstName)
//                .andOperator(Criteria.where("last_name").is(lastName)));
//        return mongoTemplate.findOne(searchUserQuery, UserDAO.class);
//    }
}
