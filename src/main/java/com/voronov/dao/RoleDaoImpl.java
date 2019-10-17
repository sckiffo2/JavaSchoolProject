package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.RoleDao;
import com.voronov.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDaoImpl implements RoleDao {
	@Override
	public Role findById(int id) {
		//todo implement
		return null;
	}

	@Override
	public Role findByName(String name) {
		return null;
	}

	@Override
	public List<Role> findAll() {
		return null;
	}

	@Override
	public void save(Role role) {

	}

	@Override
	public void update(Role role) {

	}

	@Override
	public void delete(Integer id) {

	}
}
