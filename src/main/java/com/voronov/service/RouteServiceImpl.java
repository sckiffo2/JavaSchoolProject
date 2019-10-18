package com.voronov.service;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteDao routeDao;

	@Override
	public Route findById(int id) {
		return routeDao.findById(id);
	}

	@Override
	public Route findByName(String name) {
		return routeDao.findByName(name);
	}

	@Override
	public void save(Route route) {
		routeDao.save(route);
	}

	@Override
	public void update(Route route) {
		routeDao.update(route);
	}

	@Override
	public void delete(int id) {
		Route deleteRoute = routeDao.findById(id);
		routeDao.delete(deleteRoute);
	}

	@Override
	public List<Route> findAll() {
		return routeDao.findAll();
	}
}
