package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Trip;

import java.util.List;

public interface TripService {
	Trip findById(int id);

	List<Trip> findByRouteId(int id);

	List<Trip> getScheduleOfStation(int id);

	void delete(int id);

	List<Trip> findAll();

	void setTripDao(TripDao tripDao);
}
