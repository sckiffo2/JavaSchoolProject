package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.RoleDao;
import com.voronov.entities.Role;

import java.util.List;

public interface RoleService {
	/***
	 * find and retrieve Role entity by id
	 * @param id Role id
	 * @return Role
	 */
	Role findById(long id);

	/***
	 * find and retrieve Role entity by name
	 * @param name Role name
	 * @return Role
	 */
	Role findByName(String name);

	/***
	 * retrieve all Role entities
	 * @return list of all Role what we have in DB
	 */
	List<Role> findAll();

	void setRoleDao(RoleDao roleDao);
}
