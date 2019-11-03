package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.Role;
import com.voronov.entities.User;

import java.util.List;

public interface UserService {
    User findById(long id);

    User findByName(String name);

	boolean isExists(User user);

	List<User> findAll();

	List<Role> findAllRoles();

	void addUser(User user);

    void update(User user);

    void delete(long id);

    void setUserDao(UserDao userDao);
}
