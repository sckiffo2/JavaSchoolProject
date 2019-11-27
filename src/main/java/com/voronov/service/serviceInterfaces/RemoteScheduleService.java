package com.voronov.service.serviceInterfaces;

import com.voronov.dto.RemoteScheduleDto;
import com.voronov.entities.Station;

import java.util.List;

public interface RemoteScheduleService {
	/***
	 * produces list of RemoteScheduleDto what represent rows of Schedule for requested station
	 * @param id Station id
	 * @return list of RemoteScheduleDto
	 */
	List<RemoteScheduleDto> getSchedule(long id);

	/***
	 * retrieve Station by its id
	 * @param id Station id
	 * @return Station
	 */
	Station getStation(long id);

	List<Station> getAllStations();

	void setStationService(com.voronov.service.serviceInterfaces.StationService stationService);

	void setTripService(com.voronov.service.serviceInterfaces.TripService tripService);

}
