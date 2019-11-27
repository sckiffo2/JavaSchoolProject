package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.Role;
import com.voronov.entities.User;

import java.util.List;

public interface UserService {
	/***
	 * retrieve all User entities
	 * @return list of all users
	 */
	List<User> findAll();
	/***
	 * find and retrieve User entity by id
	 * @param id User id
	 * @return User entity
	 */
    User findById(long id);
	/***
	 * find and retrieve User entity by name
	 * @param name User name
	 * @return User entity
	 */
    User findByName(String name);
	/***
	 * retrieve all Role entities
	 * @return list of all roles
	 */
	List<Role> findAllRoles();

	/***
	 * check if User exists in DB
	 * @param user User to check
	 * @return true if User already exists
	 */
	boolean isExists(User user);

	/***
	 * create and save new User in DB
	 * @param user User to create
	 */
	void save(User user);

	void update(String username, String mail, boolean active, String role);

	/***
	 * update presented User in DB
	 * @param user User to update
	 */
    void update(User user);

	/***
	 * delete User by its id
	 * @param id User id
	 */
    void delete(long id);

    void setUserDao(UserDao userDao);
}
