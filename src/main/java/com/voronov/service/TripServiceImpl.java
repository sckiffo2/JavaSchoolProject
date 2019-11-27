package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.*;
import com.voronov.dto.TicketScheduleDTO;
import com.voronov.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TripService;
import com.voronov.service.serviceInterfaces.TripStationService;
import com.voronov.utils.JmsSender;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Setter
@NoArgsConstructor
public class TripServiceImpl implements TripService {
	public static final int TRIP_SCHEDULE_DAYS = 30;

	private static final Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);

	@Autowired
	private TripDao tripDao;

	@Autowired
	private RouteService routeService;

	@Autowired
	private StationService stationService;

	@Autowired
	private TripStationService tripStationService;

	@Autowired
	private JmsSender sender;

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
		List<Trip> tripsWithBothStations = tripsWithStationOne.stream().filter(x -> x.getStations()
				.contains(secondStation)).collect(Collectors.toList());
		List<Trip> resultTrips = new ArrayList<>();
		for (Trip trip : tripsWithBothStations) {
			if (trip.isCanceled()) {
				continue;
			}
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

	@Override
	public void save(String routeNumber, LocalDate date) {
		Route route = routeService.findByNumber(routeNumber);
		if (route == null) {
			throw new BusinessLogicException("Рейса с таким номером не существует.");
		}

		if (existsByRouteAndDate(route, date)) {
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
	public void createTripsBySchedule() {
		logger.debug("Начинаем автоматическое создание рейсов...");
		int tripsCreated = 0;
		List<Route> allRoutes = findAllRoutes();
		for (Route route : allRoutes) {
			String schedulePattern = route.getSchedulePattern();
			if (schedulePattern != null) {
				String[] pattern = schedulePattern.split(";");
				LocalDate futureDate = LocalDate.now().plusDays(TRIP_SCHEDULE_DAYS);
				boolean dateIsValid = false;
				if (pattern[0].equals("EVERYDAY")) {
					dateIsValid = true;
				}
				if (pattern[0].equals("EVEN")) {
					if (futureDate.getDayOfMonth() % 2 == 0) {
						dateIsValid = true;
					}
				}
				if (pattern[0].equals("ODD")) {
					if (futureDate.getDayOfMonth() % 2 != 0) {
						dateIsValid = true;
					}
				}
				if (pattern[0].equals("WEEK")) {
					List<String> list = Arrays.asList(pattern);
					int dayOfWeek = futureDate.getDayOfWeek().getValue();
					if (list.contains(Integer.toString(dayOfWeek))) {
						dateIsValid = true;
					}
				}
				if (pattern[0].equals("MONTH")) {
					List<String> list = Arrays.asList(pattern);
					int dayOfMonth = LocalDate.now().getDayOfMonth();
					if (list.contains(Integer.toString(dayOfMonth))) {
						dateIsValid = true;
					}
				}
				if (dateIsValid) {
					try {
						save(route.getNumber(), futureDate);
						tripsCreated++;
					} catch (BusinessLogicException e) {
						logger.debug("По какой-то причине такой рейс в этот день уже существует. Не будем создавать будликат");
					}
				}
			}
		}
		logger.debug("Создано : " + tripsCreated);
	}

	@Override
	public boolean existsByRouteAndDate(Route route, LocalDate date) {
		Trip tripToCheck = findByRouteIdAndDate(route.getId(), date);
		if (tripToCheck != null) {
			return true;
		}
		return false;
	}

	@Override
	public void update(long id, boolean canceled, int delay) {
		Trip updateTrip = tripDao.findById(id);
		updateTrip.setCanceled(canceled);
		updateTrip.setDelay(delay);
		tripDao.update(updateTrip);
		logger.info("sending JMS message successful");
		try {
			sender.sendMessage();
		} catch (JMSException e) {
			logger.error("sending JMS message failed");
		}
	}

	@Override
	public List<Trip> findAll() {
		return tripDao.findAll();
	}

	@Override
	public List<Trip> findAllActualTrips() {
		List<Trip> allTrips = tripDao.findAll();

		List<Trip> allActualTrips = allTrips.stream().filter(x -> x.getStationsOnTrip()
				.stream()
				.anyMatch(y -> (y.getDepartureTime() != null && y.getDepartureTime().plusMinutes(10).isAfter(LocalDateTime.now())) ||
						(y.getArrivalTime() != null && y.getArrivalTime().plusMinutes(10).isAfter(LocalDateTime.now())))
		).sorted().collect(Collectors.toList());
		return allActualTrips;
	}

	@Override
	public List<Route> findAllRoutes() {
		return routeService.findAll();
	}
}
