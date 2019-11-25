package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Route;
import com.voronov.entities.Trip;
import com.voronov.entities.TripStation;
import com.voronov.dto.TicketScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
	Trip findById(long id);

	Trip findByRouteIdAndDate(long id, LocalDate date);

	List<Trip> findTripsByStationId(long id);

	List<TicketScheduleDTO> findTripsByStationsAndDate(long departureStation, long arrivalStation, LocalDate date);

	List<TripStation> findTripStations(long id);

	void createTrip(String routeName, LocalDate date);

	void createTripsBySchedule();

	boolean existsByRouteAndDate(Route route, LocalDate date);

	void update(long id, boolean canceled, int delay);

	List<Trip> findAll();

	List<Trip> findAllActualTrips();

	List<Route> findAllRoutes();

	void setTripDao(TripDao tripDao);

	void setStationService(StationService stationService);

	void setTripStationService(TripStationService tripStationService);

	void setRouteService(RouteService routeService);
}
