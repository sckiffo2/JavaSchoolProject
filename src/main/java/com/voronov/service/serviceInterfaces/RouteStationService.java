package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.RouteStation;

import java.util.List;

public interface RouteStationService {

	void setRouteStationDao(RouteStationDao routeStationDao);

	RouteStation findById(long id);

	void save(long strId, String stationName, String strArrival, String strDeparture, String arrivalDayNumber, String departureDayNumber);

	void update(RouteStation station);

	void delete(long id);

	List<RouteStation> findAll();

	List<RouteStation> findStationsOfRoute(long id);
}
