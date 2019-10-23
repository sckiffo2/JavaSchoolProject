package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Route;

import java.util.List;

public interface RouteDao {
	Route findById(long id);

	Route findByName(String name);

	Route findByNumber(String number);

	@SuppressWarnings("unchecked")
	List<Route> findAll();

	List<Route> findRoutesByStationId(long id);

	void save(Route route);

	void update(Route route);

	void delete(Route route);
}
