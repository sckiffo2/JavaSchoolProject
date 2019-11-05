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
		tripService.setTripStationService(new TripStationServiceImpl());
		TripStationService tripStationService = new TripStationServiceImpl();
		tripStationService.setTripStationDao(new TripStationDaoImpl());


//		List<Trip> tripList = tripService.findByRouteId(1);
//		List<Passenger> passengersList = passengerService.findPassengersOnTrip(1);
//		List<Ticket> ticketList = ticketService.findByTripId(1);
//		List<Route> routesWithStation = routeService.findRoutesByStationId(9);

		LocalDate date = LocalDate.parse("2019-10-22");
//		List<Trip> routes = ticketService.findTripsByStationsAndDate(4, 1, date);

//		System.out.println(tripsForSchedule.get(1).getRoute().getStationsOnRoute().get(1).getStation().getName());
		String departureStation = "Санкт-Петербург";
		String arrivalStation = "Москва";
//		List<TicketScheduleDTO> list = ticketService.findTripsByStationsAndDate(departureStation, arrivalStation, date);

//		List<List<Integer>> list = ticketService.findFreePlaces(1, departureStation, arrivalStation);

		tripService.createTripsBySchedule();

//		User user = userService.findByName("admin");
		System.out.println();


		//todo древние рейсы не отображаем (отмправление менее чем через 10 минут)
		//todo user login before take trip
		//todo add succes ticket buy form
		//todo ticket pricing
		//todo
		//todo
		//todo цена билета формируется из магич числа + цена зоны * кол-во зон. Цена одной зоны хранится в trip
		//todo ticket price on tripChoose page
		//todo validation
		//todo wrong data input

		//todo logs
		//todo tests

		//todo css a without lines
	}
}
