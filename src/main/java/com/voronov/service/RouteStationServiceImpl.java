package com.voronov.service;


import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.Route;
import com.voronov.entities.RouteStation;
import com.voronov.entities.Station;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.RouteStationService;
import com.voronov.service.serviceInterfaces.StationService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class RouteStationServiceImpl implements RouteStationService {

	final private int SECONDS_IN_DAY = 86400;
	@Autowired
	private RouteStationDao routeStationDao;

	@Autowired
	private StationService stationService;

	@Autowired
	private RouteService routeService;

	@Override
	public RouteStation findById(int id) {
		return routeStationDao.findById(id);
	}

	@Override
	public void save(String strId, String stationName, String strArrival, String strDeparture, String arrivalDayNumber, String departureDayNumber) {
		RouteStation routeStation = new RouteStation();

		Route route = routeService.findById(Long.parseLong(strId));
		Station station =  stationService.findByName(stationName);

		if (!strArrival.isEmpty()) {
			int arrival = LocalTime.parse(strArrival).toSecondOfDay() + SECONDS_IN_DAY * Integer.parseInt(arrivalDayNumber);
			routeStation.setArrivalTime(arrival);
		}

		if (!strDeparture.isEmpty()) {
			int departure = LocalTime.parse(strDeparture).toSecondOfDay() + SECONDS_IN_DAY * Integer.parseInt(departureDayNumber);
			routeStation.setDepartureTime(departure);
		}

		routeStation.setRoute(route);
		routeStation.setStation(station);

		List<RouteStation> routeStationsList = routeStationDao.findStationsOfRoute(route.getId());
		routeStation.setIndexInRoute(routeStationsList.size());
		routeStationDao.save(routeStation);
	}

	@Override
	public void update(RouteStation routeStation) {
		routeStationDao.update(routeStation);
	}

	@Override
	public void delete(int id) {
		RouteStation deleteRouteStation = routeStationDao.findById(id);
		routeStationDao.delete(deleteRouteStation);
	}

	@Override
	public List<RouteStation> findAll() {
		return routeStationDao.findAll();
	}

	@Override
	public List<RouteStation> findStationsOfRoute(int id) {
		List<RouteStation> stationsOfRoute = routeStationDao.findStationsOfRoute(id);
		stationsOfRoute.sort(Comparator.comparing(RouteStation::getIndexInRoute));
		return stationsOfRoute;
	}
}
