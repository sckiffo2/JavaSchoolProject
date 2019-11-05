package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.*;
import com.voronov.dto.TicketScheduleDTO;
import com.voronov.service.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Setter
@NoArgsConstructor
public class TicketServiceImpl implements TicketService {

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	final static int STOP_SELL_TICKETS = 10;
	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private StationService stationService;

	@Autowired
	private TripService tripService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private PassengerService passengerService;

	@Override
	public Ticket findById(long id) {
		return ticketDao.findById(id);
	}

	@Override
	public List<Ticket> findByTripId(long id) {
		return ticketDao.findByTripId(id);
	}

	@Override
	public List<TicketScheduleDTO> findTripsByStationsAndDate(String departureStationName, String arrivalStationName, String stringDate) {
		logger.debug("method start");
		LocalDate date = null;
		try {
			date = LocalDate.parse(stringDate);
		} catch (Exception e) {
			logger.error("BusinessLogicException Введена неверная дата, удостоверьтесь в правильности ввода.");
			throw new BusinessLogicException("Введена неверная дата, удостоверьтесь в правильности ввода.");
		}

		Station departureStation = stationService.findByName(departureStationName);
		Station arrivalStation = stationService.findByName(arrivalStationName);
		if (departureStation == null) {
			logger.error("BusinessLogicException Станции отправления с таким названием не существует.");
			throw new BusinessLogicException("Станции отправления с таким названием не существует.");
		}
		if (arrivalStation == null) {
			logger.error("BusinessLogicException Станции прибытия с таким названием не существует.");
			throw new BusinessLogicException("Станции прибытия с таким названием не существует.");
		}
		long departureStationId = departureStation.getId();
		long arrivalStationId = arrivalStation.getId();
		logger.debug("method end, call tripService.findTripsByStationsAndDate()");
		return tripService.findTripsByStationsAndDate(departureStationId, arrivalStationId, date);
	}

	@Override
	public List<List<Integer>> findFreePlaces(long tripId, String departureStationName, String arrivalStationName) {
		logger.debug("method start");
		Station departureStation = stationService.findByName(departureStationName);
		Station arrivalStation = stationService.findByName(arrivalStationName);

	    int numberOfWagons = 15;
		int numberOfPlacesInWagon = 36;
		List<List<Integer>> placesList = new ArrayList<>(numberOfWagons);
		for (int i = 0; i < numberOfWagons; i++) {
			List<Integer> wagonPlaces = IntStream.rangeClosed(1, numberOfPlacesInWagon).boxed().collect(Collectors.toList());
			placesList.add(wagonPlaces);
		}

		List<Ticket> ticketsOfTrip = findByTripId(tripId);
		List<TripStation> stationsOfTrip = tripService.findTripStations(tripId);

		int departureStationIndex = 0;
		int arrivalStationIndex = 0;
		Map<Station, Integer> stations = new HashMap<>();
		for (TripStation tripStation : stationsOfTrip) {
			if (tripStation.getStation().getId().equals(departureStation.getId())  ) {
				departureStationIndex = tripStation.getIndexInRoute();
				if (tripStation.getDepartureTime().minusMinutes(10).isBefore(LocalDateTime.now())) {
					logger.error("BusinessLogicException До отправления поезда осталось менее 10 минут, бронирование билета невозможно.");
					throw new BusinessLogicException("До отправления поезда осталось менее 10 минут, бронирование билета невозможно.");
				}
			} else if (tripStation.getStation().getId().equals(arrivalStation.getId())) {
				arrivalStationIndex = tripStation.getIndexInRoute();
			}
			stations.put(tripStation.getStation(), tripStation.getIndexInRoute());
		}

		//here we delete places what already taken by another tickets from PlacesList
		for (Ticket ticket : ticketsOfTrip) {
			int ticketArrivalStationIndex = stations.get(ticket.getArrivalStation());
			int ticketDepartureStationIndex = stations.get(ticket.getDepartureStation());
			if (ticketDepartureStationIndex < departureStationIndex && ticketArrivalStationIndex <= departureStationIndex){
				continue;
			} else if (ticketDepartureStationIndex >= arrivalStationIndex && ticketArrivalStationIndex > arrivalStationIndex) {
				continue;
			}
			placesList.get(ticket.getWagonNumber() - 1).remove(new Integer(ticket.getPlace()));
		}
		logger.debug("method end");
		return placesList;
	}

	@Override
	public void bookTicket(long tripId, String departureStationName, String arrivalStationName, int wagonNumber, int placeNumber) {
		logger.debug("method start");
		Ticket ticketToBook = new Ticket();
		ticketToBook.setBookedTill(LocalDateTime.now().plusMinutes(10));
		Trip trip = tripService.findById(tripId);
		ticketToBook.setTrip(tripService.findById(tripId));
		ticketToBook.setDepartureStation(stationService.findByName(departureStationName));
		ticketToBook.setArrivalStation(stationService.findByName(arrivalStationName));
		ticketToBook.setWagonNumber(wagonNumber);
		ticketToBook.setPlace(placeNumber);

		if (isPlaceFree(trip, wagonNumber, placeNumber)) {
			ticketDao.save(ticketToBook);
		} else {
			logger.error("BusinessLogicException Увы данное место уже кто-то забронировал.");
			throw new BusinessLogicException("Увы данное место уже кто-то забронировал.");
		}
		logger.debug("method end");
	}

	@Override
	public boolean isPlaceFree(Trip trip, int wagon, int place) {
		logger.debug("check if this place free");
		return ticketDao.isExists(trip, wagon, place);
	}

	@Override
	public void registerPassengerToTrip(Passenger passenger, long tripId, int wagon, int place) {
		logger.debug("Starting to register passenger on trip");
		Passenger passengerInDatabase = passengerService.findByPassengerData(passenger.getFirstName(), passenger.getLastName(), passenger.getBirthDate());
		Ticket ticket = findTicketByTripAndPlace(tripId, wagon, place);
		if (ticket.getPassenger() != null || ticket.getBookedTill() == null) {
			logger.error("BusinessLogicException Данный билет уже выкуплен.");
			throw new BusinessLogicException("Данный билет уже выкуплен.");
		}
		if (passengerInDatabase == null) {
			passengerService.save(passenger);
		} else {
			passenger = passengerInDatabase;
		}
		if (!passengerService.isPassengerOnTrip(passenger, tripId)) {
			if (!isTooLateToBuyTicket(ticket.getTrip(), ticket.getDepartureStation().getId())) {
				ticket.setPassenger(passenger);
				ticket.setBookedTill(null);
				ticketDao.update(ticket);
			} else {
				logger.error("BusinessLogicException До отправления поездла остается менее 10 минут. Покупка билета невозможна.");
				throw new BusinessLogicException("До отправления поездла остается менее 10 минут. Покупка билета невозможна.");
			}
		} else {
			logger.error("BusinessLogicException Данный пассажир уже зарегистрирован на рейс.");
			throw new BusinessLogicException("Данный пассажир уже зарегистрирован на рейс.");
		}
	}


	public boolean isTooLateToBuyTicket(Trip trip, long departureStationId) {
		logger.debug("Check if departure in less than 10min.");
		TripStation tripStation =  trip.getStationsOnTrip()
				.stream()
				.filter(x -> x.getStation().getId() == departureStationId)
				.findAny()
				.get();

		boolean late = LocalDateTime.now().isAfter(tripStation.getDepartureTime().minusMinutes(STOP_SELL_TICKETS));
		logger.debug("tooLate : " + late);
		return late;
	}

	@Override
	public Ticket findTicketByTripAndPlace(long tripId, int wagon, int place) {
		return ticketDao.findTicketByTripAndPlace(tripId, wagon, place);
	}

	public void deleteLongBookedTickets() {
		ticketDao.deleteLongBookedTickets();
	}

	@Override
	public List<Ticket> findAll() {
		return ticketDao.findAll();
	}

	@Override
	public List<Station> findAllStations() {
		return stationService.findAll();
	}
}