package com.voronov.service;


import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.Route;
import com.voronov.entities.RouteStation;
import com.voronov.entities.Station;
import com.voronov.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.RouteStationService;
import com.voronov.service.serviceInterfaces.StationService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
	public RouteStation findById(long id) {
		return routeStationDao.findById(id);
	}

	@Override
	public void save(long strId, String stationName, String strArrival, String strDeparture, String arrivalDayNumber, String departureDayNumber) {
		RouteStation routeStation = new RouteStation();

		if (strArrival.isEmpty() && strDeparture.isEmpty()) {
			throw new BusinessLogicException("У станции должно присутствовать, либо время отправления, либо время прибытия");
		}

		if (arrivalDayNumber.isEmpty()) {
			arrivalDayNumber = "0";
		}
		if (departureDayNumber.isEmpty()) {
			departureDayNumber = "0";
		}
		Route route = routeService.findById(strId);
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
	public void delete(long id) {
		RouteStation deleteRouteStation = routeStationDao.findById(id);
		routeStationDao.delete(deleteRouteStation);
	}

	@Override
	public List<RouteStation> findAll() {
		return routeStationDao.findAll();
	}

	@Override
	public List<RouteStation> findStationsOfRoute(long id) {
		List<RouteStation> stationsOfRoute = routeStationDao.findStationsOfRoute(id);
		stationsOfRoute.sort(Comparator.comparing(RouteStation::getIndexInRoute));
		return stationsOfRoute;
	}
}
