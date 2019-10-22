package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Route;
import com.voronov.entities.Trip;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class TripServiceImpl implements TripService {

	@Autowired
	private TripDao tripDao;

	@Autowired
	private RouteService routeService;

	@Override
	public Trip findById(long id) {
		return tripDao.findById(id);
	}

	@Override
	public List<Trip> findByRouteId(long id) {
		return tripDao.findByRouteId(id);
	}

	@Override
	public List<Trip> findTripsByStationId(long id) {

		List<Trip> result = tripDao.findTripsByStationId(id);

		return result;
	}

	@Override
	public void delete(long id) {
		Trip deleteTrip = tripDao.findById(id);
		tripDao.delete(deleteTrip);
	}

	@Override
	public List<Trip> findAll() {
		return tripDao.findAll();
	}
}
