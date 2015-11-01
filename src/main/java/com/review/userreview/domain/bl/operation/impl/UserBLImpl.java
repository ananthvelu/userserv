package com.review.userreview.domain.bl.operation.impl;

import com.review.userreview.domain.bl.dao.CryptoDAO;
import com.review.userreview.domain.bl.operation.UserBL;
import com.review.userreview.domain.datamodel.UserRepository;
import com.review.userreview.domain.datamodel.dto.UserDAO;
import com.review.userreview.exception.CredentialsNotFoundException;
import com.review.userreview.exception.NotFoundException;
import com.review.userreview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.security.auth.login.CredentialNotFoundException;

/**
 * User domain Business Logic goes into this class.
 */
@Service
public class UserBLImpl implements UserBL {
    @Autowired
    private UserRepository userRepository;

    @Inject
    private CryptoDAO cryptoDAO;

    @Override
    public UserDAO getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDAO getUserByFullName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public UserDAO loginCheck(UserDAO userDAO) {
        UserDAO userDAOFromDB = getUserByUsername(userDAO.getUserName());
        if (userDAOFromDB == null) {
            throw new NotFoundException("user account not found");
        } else {
            if (cryptoDAO.comparePasswordHash(userDAO.getPassword(), userDAOFromDB.getPasswordSalt(), userDAOFromDB.getPasswordHash())) {
                return userDAOFromDB;
            } else {
                throw new CredentialsNotFoundException("Credentials not found");
            }
        }
    }

    @Override
    public UserDAO createUser(UserDAO userDAO) {
        userDAO.setPasswordSalt(new String(cryptoDAO.generatePasswordSalt()));
        userDAO.setPasswordHash(cryptoDAO.generateHashFromPassword(userDAO.getPassword(), userDAO.getPasswordSalt().getBytes()));
        return userRepository.create(userDAO);
    }

    /**
     * Setter method to inject cryptoDAO
     * @param cryptoDAO cryptoDAO client
     */
    public void setCryptoDAO(CryptoDAO cryptoDAO) {
        this.cryptoDAO = cryptoDAO;
    }
}
