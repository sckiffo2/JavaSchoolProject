package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Trip;

import java.util.List;

public interface TripService {
	Trip findById(long id);

	List<Trip> findByRouteId(long id);

	List<Trip> findTripsByStationId(long id);

	void delete(long id);

	List<Trip> findAll();

	void setTripDao(TripDao tripDao);
}
