package com.voronov.dao;

import com.voronov.entities.User;

import java.util.List;

public interface UserDao {
	User findById(int id);

	User findByName(String name);

	@SuppressWarnings("unchecked")
	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(Integer id);
}
