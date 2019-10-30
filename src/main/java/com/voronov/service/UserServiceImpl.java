package com.voronov.service;


import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.Role;
import com.voronov.entities.User;
import com.voronov.service.serviceInterfaces.RoleService;
import com.voronov.service.serviceInterfaces.UserService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
	private RoleService roleService;

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void addUser(User user) {
		user.setActive(true);
		Role userRole = roleService.findByName("USER");
		List<Role> roleList = new ArrayList<>();
		roleList.add(userRole);
        user.setUserRoles(roleList);
    	userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(long id) {
        User deleteUser = userDao.findById(id);
        userDao.delete(deleteUser);
    }
}
