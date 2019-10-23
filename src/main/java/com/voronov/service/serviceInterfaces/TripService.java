package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
	Trip findById(long id);

	Trip findByRouteIdAndDate(long id, LocalDate date);

	List<Trip> findTripsByStationId(long id);

	void delete(long id);

	List<Trip> findAll();

	void setTripDao(TripDao tripDao);
}
