package com.review.userreview.domain.bl.operation;

import com.review.userreview.domain.datamodel.dto.UserDAO;
import com.review.userreview.model.User;

/**
 * User domain Business Logic goes into this class.
 */

public interface UserBL {
    UserDAO getUserByUsername(String username);

    /**
     * Create a new User in the data base.
     * @param userDAO User details
     * @return created User DAO
     */
    UserDAO createUser(UserDAO userDAO);

    /**
     * Look up and get the user details by full name.
     * @param firstName first name of the user to look up
     * @param lastName last name of the user to look up
     * @return User details if found any match.
     */
    UserDAO getUserByFullName(String firstName, String lastName);

    /**
     * Check if user credentials are correct.
     * If yes, return User details.
     * @param userDAO customer entered credential details
     * @return UserDAO details.
     */
    UserDAO loginCheck(UserDAO userDAO);
}