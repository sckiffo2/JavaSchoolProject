package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.dto.TicketScheduleDTO;
import com.voronov.entities.*;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TripService;
import com.voronov.service.serviceInterfaces.TripStationService;
import com.voronov.utils.JmsSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.JMSException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceImplTest {

	@InjectMocks
	@Spy
	TripService service = new TripServiceImpl();

	@Mock
	TripDao dao;

	@Mock
	StationService stationService;

	@Mock
	TripStationService tripStationService;

	@Mock
	RouteService routeService;

	@Mock
	JmsSender sender;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findById() {
	}

	@Test
	public void findByRouteIdAndDate() {
	}


	@Test
	public void findTripsByStationsAndDate() {
		long departureStationId = 1L;
		long arrivalStationId = 2L;
		LocalDate date = LocalDate.now();
		Trip trip1 = new Trip(LocalDate.now(), false, 0);
		Trip trip2 = new Trip(LocalDate.now(), false, 0);
		trip1.setId(1L);
		trip2.setId(2L);
		Route route1 = new Route("1A", "routeName1", "pattern1");
		Route route2 = new Route("1B", "routeName2", "pattern2");
		Station st1 = new Station(departureStationId, "Station1");
		Station st2 = new Station(arrivalStationId, "Station2");
		when(stationService.findById(arrivalStationId)).thenReturn(st2);
		TripStation ts11 = new TripStation(trip1, st1, LocalDateTime.now().plusMinutes(12), LocalDateTime.now().plusMinutes(14), 0);
		TripStation ts12 = new TripStation(trip1, st2, LocalDateTime.now().plusMinutes(16), LocalDateTime.now().plusMinutes(18), 1);
		TripStation ts21 = new TripStation(trip2, st2, LocalDateTime.now().plusMinutes(12), LocalDateTime.now().plusMinutes(14), 0);
		TripStation ts22 = new TripStation(trip2, st1, LocalDateTime.now().plusMinutes(16), LocalDateTime.now().plusMinutes(18), 1);
		trip1.setStationsOnTrip(new ArrayList<>(Arrays.asList(ts11, ts12)));
		trip2.setStationsOnTrip(new ArrayList<>(Arrays.asList(ts21, ts22)));
		trip1.setStations(new ArrayList<>(Arrays.asList(st1, st2)));
		trip2.setStations(new ArrayList<>(Arrays.asList(st2, st1)));
		trip1.setRoute(route1);
		trip2.setRoute(route2);
		List<Trip> trips = new ArrayList<>(Arrays.asList(trip1, trip2));
		when(dao.findTripsByStationId(departureStationId)).thenReturn(trips);

		List<TicketScheduleDTO> tripsResult = service.findTripsByStationsAndDate(departureStationId, arrivalStationId, date);
		assertEquals(1,tripsResult.size());
	}


	@Test
	public void findTripsByStationId() {
	}

	@Test
	public void findTripStations() {
	}

	@Test
	public void createTrip() {
		String routeNumber = "1A";
		LocalDate date = LocalDate.now();
		Trip trip1 = new Trip(LocalDate.now(), false, 0);
		Station st1 = new Station(1L, "Station1");
		Station st2 = new Station(2L, "Station2");
		Route route1 = new Route(routeNumber, "name", "pattern");
		RouteStation rs1 = new RouteStation(route1, st1, 600, 700, 0);
		RouteStation rs2 = new RouteStation(route1, st2, 800, 900, 1);
		route1.setStationsOnRoute(new ArrayList<>(Arrays.asList(rs1,rs2)));

		when(routeService.findByNumber(routeNumber)).thenReturn(route1);
		doReturn(false).when(service).existsByRouteAndDate(route1, date);
//		Trip trip = new Trip(route1, date);
//		TripStation ts1 = new TripStation(trip, st1, date.atStartOfDay().plusSeconds(600), date.atStartOfDay().plusSeconds(700), 0);
//		TripStation ts2 = new TripStation(trip, st1, date.atStartOfDay().plusSeconds(800), date.atStartOfDay().plusSeconds(900), 1);
//		trip1.setStationsOnTrip(new ArrayList<>(Arrays.asList(ts1, ts2)));

		service.createTrip(routeNumber, date);
		verify(tripStationService, times(route1.getStationsOnRoute().size())).save(new TripStation());

	}

	@Test
	public void createTripsBySchedule() {
		LocalDate futureDate = LocalDate.now().plusDays(TripServiceImpl.TRIP_SCHEDULE_DAYS);
		int dayOfWeek = futureDate.getDayOfWeek().getValue();
		int dayOfMonth = futureDate.getDayOfMonth();
		Route route1 = new Route("1", "name1", "EVEN");//not match
		Route route2 = new Route("2", "name2", "ODD");//match
		Route route3 = new Route("3", "name3", "WEEK;" + dayOfWeek);//match
		Route route4 = new Route("4", "name4", "MONTH;" + dayOfMonth);//match
		Route route5 = new Route("5", "name5", "EVERYDAY;");//match
		List<Route> routes = new ArrayList<>(Arrays.asList(route1, route2, route3, route4, route5));
		doReturn(routes).when(service).findAllRoutes();

		service.createTripsBySchedule();
		verify(service,times(4)).createTrip(anyString(), eq(futureDate));
	}

	@Test
	public void existsByRouteNumberAndDate() {
		Route route = new Route();
		route.setId(1L);
		doReturn(new Trip()).when(service).findByRouteIdAndDate(anyLong(), anyObject());
		service.existsByRouteAndDate(route, LocalDate.now());
		verify(service,times(1)).findByRouteIdAndDate(anyLong(), anyObject());
		doReturn(false).when(service).existsByRouteAndDate(anyObject(), anyObject());
		assertFalse(service.existsByRouteAndDate(anyObject(), anyObject()));
	}

	@Test
	public void delete() {
	}

	@Test
	public void update() {
		long id = 1L;
		boolean canceled = new Random().nextBoolean();
		int delay = new Random().nextInt(1001);
		when(dao.findById(id)).thenReturn(new Trip());
		service.update(id, canceled, delay);

		try {
			verify(sender, times(1)).sendMessage();
		} catch (JMSException e) {
		}
	}

	@Test
	public void findAll() {
		service.findAll();
		verify(dao, times(1)).findAll();
	}

	@Test
	public void findAllActualTrips() {
		Trip trip1 = new Trip(LocalDate.now(), false, 0);
		Trip trip2 = new Trip(LocalDate.now(), false, 0);
		trip1.setId(1L);
		trip2.setId(2L);
		Route route1 = new Route("1A", "routeName1", "pattern1");
		Route route2 = new Route("1B", "routeName2", "pattern2");
		Station st1 = new Station(1L, "Station1");
		Station st2 = new Station(2L, "Station2");
		TripStation ts11 = new TripStation(trip1, st1, LocalDateTime.now().minusMinutes(12), LocalDateTime.now().minusMinutes(14), 0);
		TripStation ts12 = new TripStation(trip1, st2, LocalDateTime.now().minusMinutes(16), LocalDateTime.now().minusMinutes(18), 1);
		TripStation ts21 = new TripStation(trip2, st2, LocalDateTime.now().plusMinutes(12), LocalDateTime.now().plusMinutes(14), 0);
		TripStation ts22 = new TripStation(trip2, st1, LocalDateTime.now().plusMinutes(16), LocalDateTime.now().plusMinutes(18), 1);
		trip1.setStationsOnTrip(new ArrayList<>(Arrays.asList(ts11, ts12)));
		trip2.setStationsOnTrip(new ArrayList<>(Arrays.asList(ts21, ts22)));
		List<Trip> trips = new ArrayList<>(Arrays.asList(trip1, trip2));

		when(dao.findAll()).thenReturn(trips);
		List<Trip> resultList = service.findAllActualTrips();
		assertEquals(1, resultList.size());
	}

	@Test
	public void findAllRoutes() {
//		service.findAllRoutes();
//		verify(routeService, times(1)).findAll();
	}
}