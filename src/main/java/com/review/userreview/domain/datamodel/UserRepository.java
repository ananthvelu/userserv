package com.review.userreview.domain.datamodel;


import com.review.userreview.domain.datamodel.dto.UserDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends MongoRepository<UserDAO, String>, UserRepositoryCustom {
    UserDAO findByUsername(String username);
    UserDAO findByFirstNameAndLastName(String firstName, String lastName);
}