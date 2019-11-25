package com.voronov.service.serviceInterfaces;

import com.voronov.dto.RemoteScheduleDto;
import com.voronov.entities.Station;

import java.util.List;

public interface RemoteScheduleService {
	List<RemoteScheduleDto> getSchedule(long id);

	void setStationService(com.voronov.service.serviceInterfaces.StationService stationService);

	void setTripService(com.voronov.service.serviceInterfaces.TripService tripService);

	Station getStation(long id);
}
