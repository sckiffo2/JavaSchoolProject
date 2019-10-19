package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Trip;

import java.util.List;

public interface TripDao {
	Trip findById(int id);

	List<Trip> findByRouteId(int id);

	@SuppressWarnings("unchecked")
	List<Trip> findAll();

	void save(Trip trip);

	void update(Trip trip);

	void delete(Trip trip);
}
