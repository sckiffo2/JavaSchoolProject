package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.Station;
import com.voronov.entities.Ticket;
import com.voronov.entities.Trip;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {

	void setTicketDao(TicketDao ticketDao);

	void setRouteService(RouteService routeService);

	void setTripService(TripService tripService);

	void setStationService(StationService stationService);

	Ticket findById(long id);

	List<Ticket> findByTripId(long id);

	List<Trip> findTripsByStationsAndDate(String firstStation, String secondStation, LocalDate departureDate);

	void bookTicket(Ticket ticket);

	void buyTicket(Ticket ticket);

	void delete(long id);

	List<Ticket> findAll();

	List<Station> findAllStations();
}
