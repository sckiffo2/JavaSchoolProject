package com.voronov.service;

import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.Route;
import com.voronov.entities.RouteStation;
import com.voronov.entities.Station;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.RouteStationService;
import com.voronov.service.serviceInterfaces.StationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteStationServiceImplTest {

	@InjectMocks
	@Spy
	RouteStationService service = new RouteStationServiceImpl();

	@Mock
	RouteStationDao dao;

	@Mock
	RouteService routeService;

	@Mock
	StationService stationService;

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
	public void save() {
		long id = 1L;
		String stationName = "StationName";
		String strArrival = "01:01";
		String strDeparture = "01:02";
		String arrivalDayNumber = "";
		String departureDayNumber = "";
		Route route = new Route();
		route.setId(id);
		when(routeService.findById(id)).thenReturn(route);
		when(stationService.findByName(stationName)).thenReturn(new Station(stationName));
		service.save(id, stationName, strArrival, strDeparture, arrivalDayNumber, departureDayNumber);
		Mockito.verify(dao, times(1)).save(eq(new RouteStation()));

	}

	@Test
	public void update() {
		service.update(new RouteStation());
		verify(dao, times(1)).update(new RouteStation());
	}

	@Test
	public void delete() {
		when(dao.findById(1L)).thenReturn(new RouteStation());
		service.delete(1L);
		verify(dao, times(1)).delete(eq(new RouteStation()));
	}

	@Test
	public void findAll() {
		service.findAll();
		verify(dao, times(1)).findAll();
	}

	@Test
	public void findStationsOfRoute() {
		service.findStationsOfRoute(1L);
		verify(dao, times(1)).findStationsOfRoute(1L);
	}
}