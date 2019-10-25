package com.voronov;

import com.voronov.dao.*;
import com.voronov.entities.*;
import com.voronov.service.*;
import com.voronov.service.serviceInterfaces.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class App {
	public static void main(String[] args) {
		StationService stationService = new StationServiceImpl();
		RouteService routeService = new RouteServiceImpl();
		RouteStationService routeStationService = new RouteStationServiceImpl();
		PassengerService passengerService = new PassengerServiceImpl();
		TripService tripService = new TripServiceImpl();
		TicketService ticketService = new TicketServiceImpl();
		stationService.setStationDao(new StationDaoImpl());
		routeService.setRouteDao(new RouteDaoImpl());
		routeStationService.setRouteStationDao(new RouteStationDaoImpl());
		passengerService.setPassengerDao(new PassengerDaoImpl());
		tripService.setTripDao(new TripDaoImpl());
		ticketService.setTicketDao(new TicketDaoImpl());
		stationService.setTripService(tripService);

//		List<Trip> tripList = tripService.findByRouteId(1);
//		List<Passenger> passengersList = passengerService.findPassengersOnTrip(1);
//		List<Ticket> ticketList = ticketService.findByTripId(1);
//		List<Route> routesWithStation = routeService.findRoutesByStationId(9);

		LocalDate date = LocalDate.parse("2019-10-23");

		List<StationScheduleDTO> scheduleOfStation = stationService.getScheduleOfStation(9, date);

//		System.out.println(tripsForSchedule.get(1).getRoute().getStationsOnRoute().get(1).getStation().getName());
		System.out.println();
	}
}
