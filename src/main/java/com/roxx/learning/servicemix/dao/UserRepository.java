/**
 * 
 */
package com.roxx.learning.servicemix.dao;

import java.util.List;

import com.roxx.learning.servicemix.domain.User;

/**
 * @author julien
 *
 */
public interface UserRepository {

	/**
	 * Create a new user in the database
	 * 
	 * @param user
	 * @return the created user containing the id
	 */
	User createUser(User user);

	/**
	 * Find a user by its name.<br>
	 * 
	 * @param name
	 * @return a list matching users or an empty list. Never returns
	 *         <code>null</code>
	 */
	List<User> findByName(String name);

	/**
	 * Returns the user matching exactly the name.<br>
	 * 
	 * @param name
	 * @return an optional containing the user found or nothing if no matching
	 *         user was found.
	 * @throws NonUniqueResultException
	 *             if multiple users ware found with the name
	 */
	User getByName(String name);

}
