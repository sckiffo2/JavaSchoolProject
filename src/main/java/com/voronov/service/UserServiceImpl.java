package com.voronov.service;


import com.sun.org.apache.xpath.internal.operations.Bool;
import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.Role;
import com.voronov.entities.User;
import com.voronov.service.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.RoleService;
import com.voronov.service.serviceInterfaces.UserService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;

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
	public boolean isExists(User user) {
    	if (user.getUsername() != null || !user.getUsername().isEmpty()) {
    		if (user.getMail() != null || !user.getMail().isEmpty()) {
				return userDao.isExists(user.getUsername(), user.getMail());
			}
		}
    	return false;
	}

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

	@Override
	public List<Role> findAllRoles() {
		return roleService.findAll();
	}

    @Override
    public void addUser(User user) {
		if (!isExists(user)) {
			user.setActive(true);
			user.setPassword("{noop}" + user.getPassword());
			List<Role> roleList = new ArrayList<>();
			Role userRole = roleService.findByName("USER");
			roleList.add(userRole);
			user.setUserRoles(roleList);
			userDao.save(user);
		} else {
			throw new BusinessLogicException("такой пользователь уже существует");
		}
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
