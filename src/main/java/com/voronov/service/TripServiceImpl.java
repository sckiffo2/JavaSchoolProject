package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.*;
import com.voronov.dto.TicketScheduleDTO;
import com.voronov.service.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TripService;
import com.voronov.service.serviceInterfaces.TripStationService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

	@Autowired
	private TripStationService tripStationService;

	@Override
	public Trip findById(long id) {
		return tripDao.findById(id);
	}

	@Override
	public Trip findByRouteIdAndDate(long id, LocalDate date) {
		return tripDao.findByRouteIdAndDate(id, date);
	}

	@Override
	public List<TicketScheduleDTO> findTripsByStationsAndDate(long departureStationId, long arrivalStationId, LocalDate date) {
		if (departureStationId == arrivalStationId) {
			return new ArrayList<>();
		}

		Station secondStation = stationService.findById(arrivalStationId);

		List<Trip> tripsWithStationOne = tripDao.findTripsByStationId(departureStationId);
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
					if (tripStation.getStation().getId() == departureStationId) {
						departureStation = tripStation;
					}
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
			ticketScheduleDTO.setCanceled(trip.isCanceled());
			for (TripStation tripStation : trip.getStationsOnTrip()) {
				if (tripStation.getStation().getId() == departureStationId) {
					ticketScheduleDTO.setDepartureStation(tripStation.getStation().getName());
					ticketScheduleDTO.setDeparture(tripStation.getDepartureTime());
				}
				if (tripStation.getStation().getId() == arrivalStationId) {
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

	public void createTrip(String routeNumber, LocalDate date) {
		Route route = routeService.findByNumber(routeNumber);
		Trip tripToCheck = findByRouteIdAndDate(route.getId(), date);
		if (tripToCheck != null) {
			throw new BusinessLogicException("Такой рейс уже существует.");
		}
		Trip trip = new Trip(route, date);
		List<TripStation> stationsList = new ArrayList<>();
		for (RouteStation routeStation : route.getStationsOnRoute()) {
			TripStation tripStation = new TripStation();
			tripStation.setTrip(trip);
			tripStation.setIndexInRoute(routeStation.getIndexInRoute());
			tripStation.setStation(routeStation.getStation());
			LocalDateTime tripStartDate = date.atStartOfDay();
			if (routeStation.getDepartureTime() != null) {
				tripStation.setDepartureTime(tripStartDate.plusSeconds(routeStation.getDepartureTime()));
			}
			if (routeStation.getArrivalTime() != null) {
				tripStation.setArrivalTime(tripStartDate.plusSeconds(routeStation.getArrivalTime()));
			}
			stationsList.add(tripStation);
		}
		tripDao.save(trip);
		stationsList.forEach(tripStationService::save);
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

	@Override
	public List<Trip> findAllFutureTrips() {
		return tripDao.findAllFutureTrips();
	}

	@Override
	public List<Route> findAllRoutes() {
		return routeService.findAll();
	}
}
