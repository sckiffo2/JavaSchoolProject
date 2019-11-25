package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.RouteStation;

import java.util.List;

public interface RouteStationDao {

	RouteStation findById(long id);

	List<RouteStation> findAll();

	public List<RouteStation> findStationsOfRoute(long id);

	void save(RouteStation routeStation);

	void update(RouteStation routeStation);

	void delete(RouteStation routeStation);
}
