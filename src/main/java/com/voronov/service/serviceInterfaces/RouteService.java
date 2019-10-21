package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;

import java.util.List;

public interface RouteService {

	void setRouteDao(RouteDao routeDao);

	Route findById(int id);

	Route findByName(String name);

	List<Route> findRouteByStationId(int id);

	void save(Route passenger);

	void update(Route passenger);

	void delete(int id);

	List<Route> findAll();
}

