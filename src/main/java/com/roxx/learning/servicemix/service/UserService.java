package com.roxx.learning.servicemix.service;

import java.util.List;

import com.roxx.learning.servicemix.domain.User;

/**
 * @author julien
 *
 */
public interface UserService {

	public User getUserByExactName(String name) throws NoUserMatchingException;

	public User getUserById(Long userId) throws UserNotExistException;

	public List<User> findUsersByName(String name);

}
