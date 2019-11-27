package com.voronov.service;

import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.dto.StationScheduleDto;
import com.voronov.entities.Route;
import com.voronov.entities.RouteStation;
import com.voronov.entities.Station;
import com.voronov.entities.Trip;
import com.voronov.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.StationService;
import com.voronov.service.serviceInterfaces.TripService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceImplTest {

	@InjectMocks
	StationService service = new StationServiceImpl();

	@Mock
	StationDao dao;

	@Mock
	TripService tripService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findById() {
		service.findById(1L);
		verify(dao, times(1)).findById(1L);
	}

	@Test
	public void findByName() {
		service.findByName("Москва");
		verify(dao, times(1)).findByName("Москва");
	}

	@Test(expected = BusinessLogicException.class)
	public void getScheduleOfStation_noStation() {
		service.getScheduleOfStation("NOT_IN_BASE", LocalDate.now());
	}

	@Test
	public void getScheduleOfStation() {
		Station station = new Station(1L, "Москва");
		Trip trip1 = new Trip(LocalDate.now(), false, 0);
		Trip trip2 = new Trip(LocalDate.now(), false, 0);
		Route route1 = new Route("1A", "routeName1", "pattern1");
		Route route2 = new Route("1B", "routeName2", "pattern2");
		RouteStation routeStation11 = new RouteStation(route1, station, 3600, 3660, 0);
		RouteStation routeStation12 = new RouteStation(route1, station, 5600, 5660, 1);
		RouteStation routeStation21 = new RouteStation(route2, station, 7600, 7660, 0);
		RouteStation routeStation22 = new RouteStation(route2, station, 8600, 8660, 1);
		route1.setStationsOnRoute(new ArrayList<>(Arrays.asList(routeStation11, routeStation12)));
		route2.setStationsOnRoute(new ArrayList<>(Arrays.asList(routeStation21, routeStation22)));
		trip1.setRoute(route1);
		trip2.setRoute(route2);
		List<Trip> tripList = new ArrayList<>(Arrays.asList(trip1, trip2));


		when(dao.findByName("Москва")).thenReturn(new Station(1L, "Москва"));
		when(tripService.findTripsByStationId(1L)).thenReturn(tripList);
		List<StationScheduleDto> result = service.getScheduleOfStation("Москва", LocalDate.now());
		verify(tripService, times(1)).findTripsByStationId(1L);
		assertEquals(tripList.size(), result.size());
	}

	@Test
	public void isExist() {
		when(dao.isExist("in_base_station_name")).thenReturn(true);
		when(dao.isExist("not_in_base_station_name")).thenReturn(false);

		assertTrue(service.isExist("in_base_station_name"));
		assertFalse(service.isExist("not_in_base_station_name"));
	}

	@Test
	public void createStation_success() {
		service.save("test-test-station");
		verify(dao, times(1)).save(new Station("test-test-station"));
	}

	@Test(expected = BusinessLogicException.class)
	public void createStation_fail() {
		when(dao.isExist("Москва")).thenReturn(true);
		service.save("Москва");
		verify(dao, times(0)).save(new Station("test-test-station"));
	}

	@Test
	public void updateStation() {
		Station station = new Station("test");
		service.update(station);
		verify(dao, times(1)).update(station);
	}

	@Test
	public void findAll() {
		Station st1 = new Station(1L, "One");
		Station st2 = new Station(2L, "Two");
		Station st3 = new Station(3L, "Three");
		List<Station> list = new ArrayList<>(Arrays.asList(st1, st2, st3));

		when(dao.findAll()).thenReturn(list);

		List<Station> stations = service.findAll();

		assertEquals(list.size(), stations.size());
		verify(dao, times(1)).findAll();
	}
}