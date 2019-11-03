package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.User;

import java.util.List;

public interface UserDao {
	User findById(long id);

	User findByName(String name);

	boolean isExists(String username, String mail);

	@SuppressWarnings("unchecked")
	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(User user);
}
