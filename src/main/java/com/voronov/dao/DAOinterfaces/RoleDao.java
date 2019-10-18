package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Role;

import java.util.List;

public interface RoleDao {
	Role findById(int id);

	Role findByName(String name);

	@SuppressWarnings("unchecked")
	List<Role> findAll();

	void save(Role role);

	void update(Role role);

	void delete(Role role);
}
