package com.review.userreview.domain.datamodel;

import com.review.userreview.domain.datamodel.dto.UserDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * This class takes care of User table management and interactions with the data source.
 */

public interface UserRepositoryCustom {
    UserDAO create(UserDAO todo);
    //UserDAO getUserByFullName(String firstName, String lastName);
}