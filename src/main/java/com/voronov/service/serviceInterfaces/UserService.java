package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.User;

import java.util.List;

public interface UserService {
    User findById(int id);

    User findByName(String name);

    List<User> findAll();

    void addUser(User user);

    void update(User user);

    void delete(int id);

    void setUserDao(UserDao userDao);
}
