package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;

import java.util.List;

public interface RouteService {
	/***
	 * retrieve all Route entities
	 * @return list of all routes
	 */
	List<Route> findAll();

	/***
	 * find and retrieve Route entity by id
	 * @param id Route id
	 * @return Route entity
	 */
	Route findById(long id);

	/***
	 * retrieve Route entity by name
	 * @param name Route name field
	 * @return Route entity
	 */
	Route findByName(String name);

	/***
	 * retrieve Route entity by number
	 * @param number Route number field
	 * @return Route entity
	 */
	Route findByNumber(String number);

	/***
	 * find all Route entities what have needed Station
	 * @param id Station id field
	 * @return list of Route
	 */
	List<Route> findRoutesByStationId(long id);

	/***
	 * check if Route with presented name exists in DB
	 * @param name Route name field
	 * @return
	 */
	boolean isExist(String name);

	/***
	 * create and save new Route in DB
	 * @param number Route number field
	 * @param name Route name field
	 * @param pattern pattern what define schedule of this Route.
	 *                The schedulePattern allows to save new Trip
	 *                entities automatically depends on its content
	 */
	void save(String number, String name, String pattern);

	/***
	 * update presented Route in DB
	 * @param route
	 */
	void update(Route route);

	/***
	 * delete Route by its id
	 * @param id Route id
	 */
	void delete(int id);

	void setRouteDao(RouteDao routeDao);
}

