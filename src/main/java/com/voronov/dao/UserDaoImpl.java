package com.voronov.dao;

import com.voronov.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImpl implements UserDao{
	@Override
	public User findById(int id) {
		//todo implement
		return null;
	}

	@Override
	public User findByName(String name) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public void save(User user) {

	}

	@Override
	public void update(User user) {

	}

	@Override
	public void delete(Integer id) {

	}
}
