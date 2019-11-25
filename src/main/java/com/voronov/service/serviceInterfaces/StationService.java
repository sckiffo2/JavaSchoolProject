package com.voronov.service.serviceInterfaces;

import com.voronov.dto.StationScheduleDto;
import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;

import java.time.LocalDate;
import java.util.List;

public interface StationService {

	void setStationDao(StationDao stationDao);

	void setTripService(TripService tripService);

	Station findById(long id);

	Station findByName(String name);

	List<StationScheduleDto> getScheduleOfStation(String name, LocalDate date);

	boolean isExist(String name);

	void createStation(String name);

	void updateStation(Station station);

	List<Station> findAll();
}
