package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.*;
import com.voronov.dto.TicketScheduleDTO;
import com.voronov.service.serviceInterfaces.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
	public List<TicketScheduleDTO> findTripsByStationsAndDate(String departureStation, String arrivalStation, LocalDate date) {
		long departureStationId = stationService.findByName(departureStation).getId();
		long arrivalStationId = stationService.findByName(arrivalStation).getId();
		return tripService.findTripsByStationsAndDate(departureStationId, arrivalStationId, date);
	}

	@Override
	public List<List<Integer>> findFreePlaces(long tripId, String departureStationName, String arrivalStationName) {
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
					// todo Exception до отправления поезда осталось менее 10 минут, бронирование билета невозможно
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
		return placesList;
	}

	@Override
	public void bookTicket(long tripId, String departureStationName, String arrivalStationName, int wagonNumber, int placeNumber) {
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
			//todo throw Business logic exception
		}
	}

	@Override
	public boolean isPlaceFree(Trip trip, int wagon, int place) {
		return ticketDao.isExists(trip, wagon, place);
	}

	@Override
	public void registerPassengerToTrip(Passenger passenger, long tripId, int wagon, int place) {
		Passenger passengerInDatabase = passengerService.findByPassengerData(passenger.getFirstName(), passenger.getLastName(), passenger.getBirthDate());
		Ticket ticket = findTicketByTripAndPlace(tripId, wagon, place);
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
				System.out.println("До отправления поездла остается менее 10 минут. Покупка билета невозможна.");
				//todo too late exception
			}
		} else {
			System.out.println("Данный пассажир уже зарегистрирован на рейс");
			//todo already on trip exception
		}
	}


	public boolean isTooLateToBuyTicket(Trip trip, long departureStationId) {
		TripStation tripStation =  trip.getStationsOnTrip()
				.stream()
				.filter(x -> x.getStation().getId() == departureStationId)
				.findAny()
				.get();

		boolean before = LocalDateTime.now().isAfter(tripStation.getDepartureTime().minusMinutes(STOP_SELL_TICKETS));
		System.out.println();
		return before;
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