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
	public Trip findById(int id) {
		return tripDao.findById(id);
	}

	@Override
	public List<Trip> findByRouteId(int id) {
		return tripDao.findByRouteId(id);
	}

	@Override
	public List<Trip> getScheduleOfStation(int id) {

		List<Route> routesWithStation = routeService.findRouteByStationId(id);
		//todo getScheduleOfStation

		List<Trip> result = new ArrayList<>();
		return result;
	}

	@Override
	public void delete(int id) {
		Trip deleteTrip = tripDao.findById(id);
		tripDao.delete(deleteTrip);
	}

	@Override
	public List<Trip> findAll() {
		return tripDao.findAll();
	}
}
