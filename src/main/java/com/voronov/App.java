package com.voronov;

import com.voronov.dao.*;
import com.voronov.entities.*;
import com.voronov.service.*;
import com.voronov.service.serviceInterfaces.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) {
		StationService stationService = new StationServiceImpl();
		RouteService routeService = new RouteServiceImpl();
		RouteStationService routeStationService = new RouteStationServiceImpl();
		PassengerService passengerService = new PassengerServiceImpl();
		TripService tripService = new TripServiceImpl();
		TicketService ticketService = new TicketServiceImpl();
		UserService userService = new UserServiceImpl();
		stationService.setStationDao(new StationDaoImpl());
		routeService.setRouteDao(new RouteDaoImpl());
		routeStationService.setRouteStationDao(new RouteStationDaoImpl());
		passengerService.setPassengerDao(new PassengerDaoImpl());
		tripService.setTripDao(new TripDaoImpl());
		tripService.setStationService(stationService);
		tripService.setRouteService(routeService);
		ticketService.setTicketDao(new TicketDaoImpl());
		stationService.setTripService(tripService);
		ticketService.setRouteService(routeService);
		ticketService.setStationService(stationService);
		ticketService.setTripService(tripService);
		userService.setUserDao(new UserDaoImpl());
		tripService.setTripStationService(new TripStationServiceImpl());
		TripStationService tripStationService = new TripStationServiceImpl();
		tripStationService.setTripStationDao(new TripStationDaoImpl());

		List<Trip> list = tripService.findAllActualTrips();
		list = list.stream().filter(x -> x.getStationsOnTrip()
				.stream()
				.anyMatch(y -> (y.getDepartureTime() != null && y.getDepartureTime().plusMinutes(10).isAfter(LocalDateTime.now())) ||
								(y.getArrivalTime() != null && y.getArrivalTime().plusMinutes(10).isAfter(LocalDateTime.now())))
		).collect(Collectors.toList());

		System.out.println();

		//todo remove sout from second app
		//todo redo remote schedule and schedule logic of today schedule (trip already started in past but still on the way)
		//todo check error handling
		//todo second app logging
		//todo add session id in logging
		//todo error redirect on same page with error color message
		//todo passengers on trip with places and wagons
		//todo check 404 error handling
		//todo buy ticket to place 42
		//todo activeMQ reconnect

		//todo remove magic numbers of wagons and places
		//todo личный кабинет с покупкой билетов
		//todo user roles

		//todo add succes ticket buy form

		//todo цена билета формируется из магич числа + цена зоны * кол-во зон. Цена одной зоны хранится в trip
		//todo ticket pricing
		//todo ticket price on tripChoose page
		//todo validation
		//todo wrong data input

		//todo logs
		//todo tests
	}
}
