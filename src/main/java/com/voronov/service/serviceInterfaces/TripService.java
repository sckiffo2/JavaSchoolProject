package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Route;
import com.voronov.entities.Trip;
import com.voronov.entities.TripStation;
import com.voronov.dto.TicketScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
	/***
	 * retrieve all Trip entities
	 * @return list of all trips
	 */
	List<Trip> findAll();

	/***
	 * find all Trips what still not ended yet
	 * @return list of Trip entities
	 */
	List<Trip> findAllActualTrips();
	/***
	 * find and retrieve Trip entity by id
	 * @param id Trip id
	 * @return Trip entity
	 */
	Trip findById(long id);

	/***
	 * find Trip based on requested Route and starts in requested date
	 * @param id Route id
	 * @param date Trip start date
	 * @return Trip
	 */
	Trip findByRouteIdAndDate(long id, LocalDate date);

	/***
	 * retrieve Trip what have requested station
	 * @param id Station id
	 * @return
	 */
	List<Trip> findTripsByStationId(long id);

	/***
	 * find and retrieve list of TicketScheduleDTO entities what have both stations and departure in requested date
	 * @param departureStation departure station id
	 * @param arrivalStation arrival station id
	 * @param date departure date from departure station
	 * @return TicketScheduleDTO list
	 */
	List<TicketScheduleDTO> findTripsByStationsAndDate(long departureStation, long arrivalStation, LocalDate date);

	/***
	 * find all TripStations of requested Trip
	 * @param id Trip id
	 * @return list of TripStation what belongs to requested Trip
	 */
	List<TripStation> findTripStations(long id);

	/***
	 * check if Trip with desired Route and date exists
	 * @param route route on which the Trip is based
	 * @param date date when Trip start
	 * @return return true if in DB exists Trip with requested Route and startDate
	 */
	boolean existsByRouteAndDate(Route route, LocalDate date);

	/***
	 * retrieve all Route entities
	 * @return list of all routes
	 */
	List<Route> findAllRoutes();

	/***
	 * creates Trips what needed to be create (define by schedulePatter of Route on what this Trip based)
	 * TripServiceImpl.TRIP_SCHEDULE_DAYS define date of created Trips
	 */
	void createTripsBySchedule();

	/***
	 * create and save new Trip in DB
	 * @param routeName Route name
	 * @param date date of trip start
	 */
	void save(String routeName, LocalDate date);

	/***
	 * update presented Route in DB and push JMS message to RemoteSchedule application
	 * @param id Trip id
	 * @param canceled is this Trip canceled
	 * @param delay what lateness have this trip
	 */
	void update(long id, boolean canceled, int delay);

	void setTripDao(TripDao tripDao);

	void setStationService(StationService stationService);

	void setTripStationService(TripStationService tripStationService);

	void setRouteService(RouteService routeService);
}
