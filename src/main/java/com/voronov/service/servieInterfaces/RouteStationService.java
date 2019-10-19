package com.voronov.service.servieInterfaces;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.RouteStation;

import java.util.List;

public interface RouteStationService {

	void setRouteStationDao(RouteStationDao routeStationDao);

	RouteStation findById(int id);

	void save(RouteStation routeStation);

	void update(RouteStation station);

	void delete(int id);

	List<RouteStation> findAll();

	public List<RouteStation> findStationsOfRoute(int id);
}
