package com.voronov.service;

import com.voronov.service.serviceInterfaces.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class RemoteScheduleServiceTest {
	@InjectMocks
	@Spy
	RemoteScheduleService service = new RemoteScheduleServiceImpl();

	@Mock
	StationService stationService;

	@Mock
	TripService tripService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void getSchedule() {

	}
}