package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.RouteStation;

import java.util.List;

public interface RouteStationDao {

	RouteStation findById(long id);

	List<RouteStation> findAll();

	public List<RouteStation> findStationsOfRoute(long id);

	void save(RouteStation route);

	void update(RouteStation route);

	void delete(RouteStation route);
}
