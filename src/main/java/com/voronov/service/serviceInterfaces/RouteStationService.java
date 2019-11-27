package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.RouteStation;

import java.util.List;

public interface RouteStationService {

	/***
	 * retrieve all Route entities
	 * @return list of all routes
	 */
	List<RouteStation> findAll();

	/***
	 * find and retrieve RouteStation entity by id
	 * @param id RouteStation id
	 * @return desired RouteStation
	 */
	RouteStation findById(long id);

	/***
	 * retrieve all RouteStation what belons to requested Route
	 * @param id Route id
	 * @return list of RouteStation
	 */
	List<RouteStation> findStationsOfRoute(long id);

	/***
	 * save new RouteStation in DB
	 * @param strId RouteStation id
	 * @param stationName RouteStation name
	 * @param strArrival RouteStation arrival
	 * @param strDeparture RouteStation departure
	 * @param arrivalDayNumber RouteStation number of days what pass from Trip start till this station arrival
	 * @param departureDayNumber RouteStation number of days what pass from Trip start till this station departure
	 */
	void save(long strId, String stationName, String strArrival, String strDeparture, String arrivalDayNumber, String departureDayNumber);

	/***
	 * update presented RouteStation in DB
	 * @param station RouteStation
	 */
	void update(RouteStation station);

	/***
	 * delete RouteStation entity by its id
	 * @param id RouteStation id
	 */
	void delete(long id);

	void setRouteStationDao(RouteStationDao routeStationDao);
}
