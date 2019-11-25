package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.*;
import com.voronov.service.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.PassengerService;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TicketService;
import com.voronov.service.serviceInterfaces.TripService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

	@InjectMocks
	@Spy
	TicketService service = new TicketServiceImpl();

	@Mock
	TicketDao dao;

	@Mock
	StationService stationService;

	@Mock
	TripService tripService;

	@Mock
	PassengerService passengerService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findById() {
		service.findById(1L);
		verify(dao, times(1)).findById(1L);
	}

	@Test
	public void findAllTicketsByTripId() {
		service.findAllTicketsByTripId(1L);
		verify(dao, times(1)).findByTripId(1L);
	}

	@Test
	public void findTripsByStationsAndDate() {
		String departureStationName = "Station1";
		String arrivalStationName = "Station2";
		String stringDate = "2020-01-01";
		when(stationService.findByName(departureStationName)).thenReturn(new Station(1L, departureStationName));
		when(stationService.findByName(arrivalStationName)).thenReturn(new Station(2L, arrivalStationName));
		service.findTripsByStationsAndDate(departureStationName, arrivalStationName, stringDate);
		verify(tripService, times(1)).findTripsByStationsAndDate(1L, 2L, LocalDate.parse(stringDate));
	}

	@Test
	public void findFreePlaces() {
		int numberOfWagons = TicketServiceImpl.DEFAULT_WAGONS_AMOUNT;
		int numberOfPlacesInWagon = TicketServiceImpl.DEFAULT_PLACES_AMOUNT;
		long tripId = 1L;
		String departureStationName = "Station3";
		String arrivalStationName = "Station5";
		when(stationService.findByName("Station3")).thenReturn(new Station(3L, "Station3"));
		when(stationService.findByName("Station5")).thenReturn(new Station(5L, "Station5"));
		Trip trip = new Trip(LocalDate.now(), false, 0);
		Station st1 = new Station(1L, "Station1");
		Station st2 = new Station(2L, "Station2");
		Station st3 = new Station(3L, "Station3");
		Station st4 = new Station(4L, "Station4");
		Station st5 = new Station(5L, "Station5");
		Station st6 = new Station(6L, "Station6");
		Station st7 = new Station(7L, "Station7");
		TripStation ts1 = new TripStation(trip, st1, null, LocalDateTime.now().plusMinutes(11), 0);
		TripStation ts2 = new TripStation(trip, st2, LocalDateTime.now().plusMinutes(12), LocalDateTime.now().plusMinutes(14), 0);
		TripStation ts3 = new TripStation(trip, st3, LocalDateTime.now().plusMinutes(16), LocalDateTime.now().plusMinutes(18), 1);
		TripStation ts4 = new TripStation(trip, st4, LocalDateTime.now().plusMinutes(20), LocalDateTime.now().plusMinutes(22), 2);
		TripStation ts5 = new TripStation(trip, st5, LocalDateTime.now().plusMinutes(24), LocalDateTime.now().plusMinutes(26), 3);
		TripStation ts6 = new TripStation(trip, st6, LocalDateTime.now().plusMinutes(28), LocalDateTime.now().plusMinutes(30), 4);
		TripStation ts7 = new TripStation(trip, st7, LocalDateTime.now().plusMinutes(32), LocalDateTime.now().plusMinutes(34), 5);
		List<TripStation> tripStations = new ArrayList<>(Arrays.asList(ts1, ts2, ts3, ts4, ts5, ts6, ts7));
		trip.setStationsOnTrip(tripStations);
		Random rand = new Random();
		int wagonNumber = rand.nextInt(15) + 1;
		Ticket t1 = new Ticket(trip, st1, st7, wagonNumber, 1);//taken
		Ticket t2 = new Ticket(trip, st1, st4, wagonNumber, 2);//taken
		Ticket t3 = new Ticket(trip, st1, st3, wagonNumber, 3);//free
		Ticket t4 = new Ticket(trip, st5, st7, wagonNumber, 4);//free
		Ticket t5 = new Ticket(trip, st4, st6, wagonNumber, 5);//taken
		Ticket t6 = new Ticket(trip, st3, st5, wagonNumber, 6);//taken
		List<Ticket> tickets = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6));
		doReturn(tickets).when(service).findAllTicketsByTripId(tripId);
		when(tripService.findTripStations(tripId)).thenReturn(tripStations);

		List<List<Integer>> result = service.findFreePlaces(tripId, departureStationName, arrivalStationName);
		assertEquals(32, result.get(wagonNumber - 1).size());
	}

	@Test
	public void bookTicket() {
		Ticket ticket = new Ticket();
		doReturn(true).when(service).isPlaceFree(new Trip(), 1, 1);
		when(tripService.findById(1L)).thenReturn(new Trip());
		when(stationService.findByName("Москва")).thenReturn(new Station("Москва"));
		when(stationService.findByName("Санкт-Петербург")).thenReturn(new Station("Санкт-Петербург"));
		service.bookTicket(1L, "Москва", "Санкт-Петербург", 1, 1);
		verify(dao, times(1)).save(ticket);
	}

	@Test
	public void isPlaceFree() {
		service.isPlaceFree(new Trip(), 1, 1);
		verify(dao, times(1)).isExists(new Trip(), 1, 1);
	}

	@Test
	public void registerPassengerToTrip() {
		Ticket ticket = new Ticket();
		ticket.setTrip(new Trip());
		ticket.setDepartureStation(new Station(1L, "somename"));
		Passenger passenger = new Passenger("Ivan", "Ivanov", LocalDate.parse("1950-01-01"), 'm');
		ticket.setBookedTill(LocalDateTime.now());
		doReturn(ticket).when(service).findTicketByTripAndPlace(1L, 1, 1);
		doReturn(false).when(service).isTooLateToBuyTicket(new Trip(), 1L);
		when(passengerService.isPassengerOnTrip(passenger, 1L)).thenReturn(false);
		service.registerPassengerToTrip(passenger, 1L, 1, 1);
		verify(dao, times(1)).update(ticket);
	}

	@Test(expected = BusinessLogicException.class)
	public void registerPassengerToTrip_failTooLate() {
		Ticket ticket = new Ticket();
		Passenger passenger = new Passenger("Ivan", "Ivanov", LocalDate.parse("1950-01-01"), 'm');
		doReturn(ticket).when(service).findTicketByTripAndPlace(1L, 1, 1);
		doReturn(true).when(service).isTooLateToBuyTicket(new Trip(), 1L);
		service.registerPassengerToTrip(passenger, 1L, 1, 1);
	}

	@Test(expected = BusinessLogicException.class)
	public void registerPassengerToTrip_failAlreadyOnTrip() {
		Ticket ticket = new Ticket();
		Passenger passenger = new Passenger("Ivan", "Ivanov", LocalDate.parse("1950-01-01"), 'm');
		doReturn(ticket).when(service).findTicketByTripAndPlace(1L, 1, 1);
		when(passengerService.isPassengerOnTrip(passenger, 1L)).thenReturn(true);
		service.registerPassengerToTrip(passenger, 1L, 1, 1);
	}

	@Test(expected = BusinessLogicException.class)
	public void registerPassengerToTrip_failTicketAlreadyBought() {
		Ticket ticket = new Ticket();
		Passenger passenger = new Passenger("Ivan", "Ivanov", LocalDate.parse("1950-01-01"), 'm');
		ticket.setPassenger(passenger);
		doReturn(ticket).when(service).findTicketByTripAndPlace(1L, 1, 1);
		service.registerPassengerToTrip(passenger, 1L, 1, 1);
	}

	@Test
	public void isTooLateToBuyTicket() {
		Station station = new Station(1L, "Москва");
		Trip trip1 = new Trip(LocalDate.now(), false, 0);
		TripStation ts1 = new TripStation(trip1, station, null, LocalDateTime.now().minusMinutes(11), 0);
		trip1.setStationsOnTrip(new ArrayList<TripStation>(Arrays.asList(ts1)));
		assertTrue(service.isTooLateToBuyTicket(trip1, 1L));
	}

	@Test
	public void findTicketByTripAndPlace() {
	}

	@Test
	public void deleteLongBookedTickets() {
		service.deleteLongBookedTickets();
		verify(dao, times(1)).deleteLongBookedTickets();
	}

	@Test
	public void findAll() {
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		Ticket t3 = new Ticket();
		List<Ticket> expectedList = new ArrayList<>(Arrays.asList(t1, t2, t3));

		when(dao.findAll()).thenReturn(expectedList);

		List<Ticket> resultList = service.findAll();

		assertEquals(expectedList.size(), resultList.size());
		verify(dao, times(1)).findAll();
	}

	@Test
	public void findAllStations() {
		service.findAllStations();
		verify(stationService, times(1)).findAll();
	}
}