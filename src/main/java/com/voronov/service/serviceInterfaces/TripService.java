package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Trip;
import com.voronov.entities.TripStation;
import com.voronov.entitiesDTO.TicketScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
	Trip findById(long id);

	Trip findByRouteIdAndDate(long id, LocalDate date);

	List<Trip> findTripsByStationId(long id);

	List<TicketScheduleDTO> findTripsByStationsAndDate(long firstStationId, long secondStationId, LocalDate date);

	List<TripStation> findTripStations(long id);

	void delete(long id);

	List<Trip> findAll();

	void setTripDao(TripDao tripDao);

	void setStationService(StationService stationService);

	void setRouteService(RouteService routeService);
}
