package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.Route;
import com.voronov.entities.Station;
import com.voronov.entities.Ticket;
import com.voronov.entities.Trip;
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
import java.util.List;

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
	public List<Trip> findTripsByStationsAndDate(String departureStation, String arrivalStation, LocalDate date) {
		long departureStationId = stationService.findByName(departureStation).getId();
		long arrivalStationId = stationService.findByName(arrivalStation).getId();
		return tripService.findTripsByStationsAndDate(departureStationId, arrivalStationId, date);
	}

	@Override
	public void bookTicket(Ticket ticket) {
		ticketDao.save(ticket);
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