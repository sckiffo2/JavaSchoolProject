package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.RoleDao;
import com.voronov.entities.Role;

import java.util.List;

public interface RoleService {

	Role findById(long id);

	Role findByName(String name);

	List<Role> findAll();

	void setRoleDao(RoleDao roleDao);
}
