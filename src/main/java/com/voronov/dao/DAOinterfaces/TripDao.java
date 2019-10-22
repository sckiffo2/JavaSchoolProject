package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Trip;

import java.util.List;

public interface TripDao {
	Trip findById(long id);

	List<Trip> findByRouteId(long id);

	public List<Trip> findTripsByStationId(long id);

	@SuppressWarnings("unchecked")
	List<Trip> findAll();

	void save(Trip trip);

	void update(Trip trip);

	void delete(Trip trip);
}
