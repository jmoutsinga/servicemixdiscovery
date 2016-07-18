package com.roxx.learning.servicemix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.roxx.learning.servicemix.domain.User;

public class JPAUserRepository implements UserRepository {

	public static final String FIND_USER_BY_NAME = "User.findByName";
	public static final String GET_USER_BY_NAME = "User.getByName";

	@PersistenceContext
	private final EntityManager entityManager;

	public JPAUserRepository(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.roxx.learning.servicemix.dao.UserRepository#findByName(java.lang.
	 * String)
	 */
	@Override
	public List<User> findByName(String name) {
		Query findUserByNameQuery = entityManager.createNamedQuery(FIND_USER_BY_NAME);
		findUserByNameQuery.setParameter("pName", "%" + name + "%");
		@SuppressWarnings("unchecked")
		List<User> result = findUserByNameQuery.getResultList();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.roxx.learning.servicemix.dao.UserRepository#createUser(com.roxx.
	 * learning.servicemix.domain.User)
	 */
	@Override
	public User createUser(User user) {
		entityManager.persist(user);
		entityManager.flush();
		return user;
	}

	@Override
	public User getByName(String name) {
		Query findUserByNameQuery = entityManager.createNamedQuery(FIND_USER_BY_NAME);
		findUserByNameQuery.setParameter("pName", name);

		return (User) findUserByNameQuery.getSingleResult();

	}

}
