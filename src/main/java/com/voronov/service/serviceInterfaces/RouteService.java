package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;

import java.util.List;

public interface RouteService {

	void setRouteDao(RouteDao routeDao);

	Route findById(long id);

	Route findByName(String name);

	Route findByNumber(String name);

	List<Route> findRoutesByStationId(long id);

	boolean isExist(String name);

	void save(String number, String name, String pattern);

	void update(Route passenger);

	void delete(int id);

	List<Route> findAll();
}

