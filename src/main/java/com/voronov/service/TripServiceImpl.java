package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Route;
import com.voronov.entities.Station;
import com.voronov.entities.Trip;
import com.voronov.entities.TripStation;
import com.voronov.entitiesDTO.TicketScheduleDTO;
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
	public List<TicketScheduleDTO> findTripsByStationsAndDate(long firstStationId, long secondStationId, LocalDate date) {

		Station secondStation = stationService.findById(secondStationId);

		List<Trip> tripsWithStationOne = tripDao.findTripsByStationId(firstStationId);
		List<Trip> tripsWithBothStations = tripsWithStationOne.stream()
				.filter(x -> x.getStations().contains(secondStation))
				.collect(Collectors.toList()
				);
		List<Trip> resultTrips = new ArrayList<>();
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
				resultTrips.add(trip);
			}
		}

		List<TicketScheduleDTO> result = new ArrayList<>();

		for (Trip trip : resultTrips) {
			TicketScheduleDTO ticketScheduleDTO = new TicketScheduleDTO();
			ticketScheduleDTO.setTripId(trip.getId());
			ticketScheduleDTO.setRouteNumber(trip.getRoute().getNumber());
			ticketScheduleDTO.setRouteName(trip.getRoute().getName());
			ticketScheduleDTO.setCanceled(trip.getCanceled());
			for (TripStation tripStation : trip.getStationsOnTrip()) {
				if (tripStation.getStation().getId() == firstStationId) {
					ticketScheduleDTO.setDepartureStation(tripStation.getStation().getName());
					ticketScheduleDTO.setDeparture(tripStation.getDepartureTime());
				}
				if (tripStation.getStation().getId() == secondStationId) {
					ticketScheduleDTO.setArrivalStation(tripStation.getStation().getName());
					ticketScheduleDTO.setArrival(tripStation.getArrivalTime());
				}
			}
			result.add(ticketScheduleDTO);
		}

		return result;
	}

	@Override
	public List<Trip> findTripsByStationId(long id) {
		return tripDao.findTripsByStationId(id);
	}

	@Override
	public List<TripStation> findTripStations(long id) {
		return tripDao.findTripStationsByTripId(id);
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
