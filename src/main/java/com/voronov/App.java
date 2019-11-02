package com.voronov;

import com.voronov.dao.*;
import com.voronov.entities.*;
import com.voronov.service.*;
import com.voronov.service.serviceInterfaces.*;

import java.time.LocalDate;

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


//		List<Trip> tripList = tripService.findByRouteId(1);
//		List<Passenger> passengersList = passengerService.findPassengersOnTrip(1);
//		List<Ticket> ticketList = ticketService.findByTripId(1);
//		List<Route> routesWithStation = routeService.findRoutesByStationId(9);

		LocalDate date = LocalDate.parse("2019-10-22");
//		List<Trip> routes = ticketService.findTripsByStationsAndDate(4, 1, date);

//		System.out.println(tripsForSchedule.get(1).getRoute().getStationsOnRoute().get(1).getStation().getName());
		String departureStation = "Санкт-Петербург-Главн.";
		String arrivalStation = "Москва Октябрьская";
//		List<TicketScheduleDTO> list = ticketService.findTripsByStationsAndDate(departureStation, arrivalStation, date);

//		List<List<Integer>> list = ticketService.findFreePlaces(1, departureStation, arrivalStation);


		User user = userService.findByName("admin");
		System.out.println();

		//todo busines exceptions handle
		//todo user registration
		//todo древние рейсы не отображаем (отмправление менее чем через 10 минут)
		//todo user login before take trip
		//todo
		//todo
		//todo show all routes
		//todo show all trips
		//todo add trips(with tripStations) service and form
		//todo security
		//todo not to show trips what is already in past
		//todo business exceptions
		//todo sort station schedule
		//todo цена билета формируется из магич числа + цена зоны * кол-во зон. Цена одной зоны хранится в trip
		//todo booked ticket delete if > 10 minutes pass
		//todo ticket price on tripChoose page
		//todo buy 2 tickets same time
		//todo error listener
		//todo trip create form
		//todo validation
		//todo wrong data input

		//todo logs
		//todo tests
	}
}
