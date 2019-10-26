package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripDao {
	Trip findById(long id);

	Trip findByRouteIdAndDate(long id, LocalDate date);

	List<Trip> findTripsByStationId(long id);

	List<Trip> findTripsByStationAndDate(long firstStationId, LocalDate date);

	@SuppressWarnings("unchecked")
	List<Trip> findAll();

	void save(Trip trip);

	void update(Trip trip);

	void delete(Trip trip);
}
