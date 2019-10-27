package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.*;
import com.voronov.entitiesDTO.TicketScheduleDTO;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TicketService;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Setter
@NoArgsConstructor
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private StationService stationService;

	@Autowired
	private TripService tripService;

	@Autowired
	private RouteService routeService;

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
	public List<List<Integer>> findFreeSeats(long tripId, long departureStationId, long arrivalStationId) {
		int numberOfWagons = 15;
		int numberOfWagonSeats = 36;
		List<List<Integer>> seatsList = new ArrayList<>(numberOfWagons);
		for (int i = 0; i < numberOfWagons; i++) {
			List<Integer> wagonSeats = IntStream.rangeClosed(1, numberOfWagonSeats).boxed().collect(Collectors.toList());
			seatsList.add(wagonSeats);
		}

		List<Ticket> ticketsOfTrip = findByTripId(tripId);
		List<TripStation> stationsOfTrip = tripService.findTripStations(tripId);

		int departureStationIndex = 0;
		int arrivalStationIndex = 0;
		Map<Station, Integer> stations = new HashMap<>();
		for (TripStation tripStation : stationsOfTrip) {
			if (tripStation.getStation().getId() == departureStationId) {
				departureStationIndex = tripStation.getIndexInRoute();
			} else if (tripStation.getStation().getId() == arrivalStationId) {
				arrivalStationIndex = tripStation.getIndexInRoute();
			}
			stations.put(tripStation.getStation(), tripStation.getIndexInRoute());
		}

		//here we delete seats what already taken by another tickets
		for (Ticket ticket : ticketsOfTrip) {
			int ticketArrivalStationIndex = stations.get(ticket.getArrivalStation());
			int ticketDepartureStationIndex = stations.get(ticket.getDepartureStation());
			if (ticketDepartureStationIndex < departureStationIndex && ticketArrivalStationIndex <= departureStationIndex){
				continue;
			} else if (ticketDepartureStationIndex >= arrivalStationIndex && ticketArrivalStationIndex > arrivalStationIndex) {
				continue;
			}
			seatsList.get(ticket.getWagonNumber() - 1).remove(new Integer(ticket.getSeatNumber()));
		}

		return seatsList;
	}

	@Override
	public void bookTicket(Ticket ticket) {
		Ticket ticketToBook = new Ticket();
		ticketToBook.setBooked(true);
//		ticketToBook.setTrip();
//		ticketToBook.setDepartureStation();
//		ticketToBook.setArrivalStation();
		ticketToBook.setWagonNumber(2);
		ticketToBook.setSeatNumber(3);
//		ticketToBook.setArrivalStation();

		ticketDao.save(ticketToBook);
	}

	@Override
	public void buyTicket(Ticket ticket) {
		ticketDao.update(ticket);
	}

	@Override
	public void delete(long id) {
		Ticket deleteTicket = ticketDao.findById(id);
		ticketDao.delete(deleteTicket);
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