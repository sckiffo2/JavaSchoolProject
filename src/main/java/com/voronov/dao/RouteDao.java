package com.voronov.dao;

import com.voronov.entities.Route;

import java.util.List;

public interface RouteDao {
	Route findById(int id);

	Route findByName(String name);

	@SuppressWarnings("unchecked")
	List<Route> findAll();

	void save(Route route);

	void update(Route route);

	void delete(Integer id);
}
