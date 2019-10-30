package com.voronov.service;

import com.voronov.dao.DAOinterfaces.RoleDao;
import com.voronov.entities.Role;
import com.voronov.service.serviceInterfaces.RoleService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findById(long id) {
		return roleDao.findById(id);
	}

	@Override
	public Role findByName(String name) {
		return roleDao.findByName(name);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}
}
