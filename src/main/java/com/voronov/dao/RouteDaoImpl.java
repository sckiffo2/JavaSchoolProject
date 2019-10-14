package com.voronov.dao;

import com.voronov.entities.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteDaoImpl implements RouteDao{
	@Override
	public Route findById(int id) {
		//todo implement
		return null;
	}

	@Override
	public Route findByName(String name) {
		return null;
	}

	@Override
	public List<Route> findAll() {
		return null;
	}

	@Override
	public void save(Route route) {

	}

	@Override
	public void update(Route route) {

	}

	@Override
	public void delete(Integer id) {

	}
}
