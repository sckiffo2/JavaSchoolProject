package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Route;
import com.voronov.entities.Station;
import com.voronov.entities.Trip;
import com.voronov.entities.TripStation;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
@NoArgsConstructor
public class TripServiceImpl implements TripService {

	@Autowired
	private TripDao tripDao;

	@Autowired
	private RouteService routeService;

	@Autowired
	private StationService stationService;

	@Override
	public Trip findById(long id) {
		return tripDao.findById(id);
	}

	@Override
	public Trip findByRouteIdAndDate(long id, LocalDate date) {
		return tripDao.findByRouteIdAndDate(id, date);
	}

	@Override
	public List<Trip> findTripsByStationsAndDate(long firstStationId, long secondStationId, LocalDate date) {
		Station secondStation = stationService.findById(secondStationId);

		List<Trip> tripsWithStationOne = tripDao.findTripsByStationId(firstStationId);
		List<Trip> tripsWithBothStations = tripsWithStationOne.stream()
				.filter(x -> x.getStations().contains(secondStation))
				.collect(Collectors.toList()
				);
		List<Trip> result = new ArrayList<>();
		for (Trip trip : tripsWithBothStations) {
			TripStation departureStation = null;
			TripStation arrivalStation = null;

			for (TripStation tripStation : trip.getStationsOnTrip()){
				if (tripStation.getDepartureTime() != null && tripStation.getDepartureTime().toLocalDate().equals(date)) {
					departureStation = tripStation;
				}
				if (tripStation.getStation().equals(secondStation)) {
					arrivalStation = tripStation;
				}
			}

			if (departureStation != null && arrivalStation != null &&
					departureStation.getIndexInRoute() < arrivalStation.getIndexInRoute()) {
				result.add(trip);
			}
		}

		System.out.println();

		return result;
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
