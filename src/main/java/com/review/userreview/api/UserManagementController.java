package com.review.userreview.api;

import com.review.userreview.domain.bl.operation.UserBL;
import com.review.userreview.domain.datamodel.dto.UserDAO;
import com.review.userreview.exception.NotFoundException;
import com.review.userreview.model.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * * This controller will only deal with User's read, lifecycle and management APIs.
 */
@RestController
public class UserManagementController {

	@Inject
	private UserBL userBL;

	private Mapper dozerResponse;

	private Mapper userToDAOMap;

	@RequestMapping(value = "/user", name = "/getbyusername", method = RequestMethod.GET, params = "username")
	public User getUser(@RequestParam(value="username") String userName) {
		UserDAO userDAO = userBL.getUserByUsername(userName);
		if (userDAO != null) {
			return dozerResponse.map(userDAO, User.class);
		} else {
			throw new NotFoundException(userName);
		}
	}

	@RequestMapping(value = "/user", name = "/getbyname", method = RequestMethod.GET, params = {"firstname", "lastname"})
	public User getUserByFullName(@RequestParam(value = "firstname") String firstName,
						@RequestParam(value = "lastname") String lastName) {
		UserDAO userDAO = userBL.getUserByFullName(firstName, lastName);
		if (userDAO != null) {
			return dozerResponse.map(userDAO, User.class);
		} else {
			throw new NotFoundException(firstName + lastName);
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public User createUser(@RequestBody @Valid final User user) {
		UserDAO userDAO;
		userDAO = userToDAOMap.map(user, UserDAO.class);

		UserDAO createUser = userBL.createUser(userDAO);
		return dozerResponse.map(createUser, User.class);
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public User login(@RequestBody @Valid final User user) {
		UserDAO userDAO;
		userDAO = userToDAOMap.map(user, UserDAO.class);

		UserDAO userFound = userBL.loginCheck(userDAO);
		return dozerResponse.map(userFound, User.class);
	}

	@Inject
	@Qualifier("dtoToResponseMapper")
	private void setDozerResponse(Mapper dozerResponse) {
		this.dozerResponse = dozerResponse;
	}

	@Inject
	@Qualifier("userToDTOResponseMapper")
	private void setUserToDAOMap(Mapper userToDAOMap) {
		this.userToDAOMap = userToDAOMap;
	}

}
