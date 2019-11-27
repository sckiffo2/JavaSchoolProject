package com.voronov.service.serviceInterfaces;

import com.voronov.dto.StationScheduleDto;
import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;

import java.time.LocalDate;
import java.util.List;

public interface StationService {
	/***
	 * retrieve all Station entities
	 * @return list of all stations
	 */
	List<Station> findAll();

	/***
	 * retrieve Station entity by its id
	 * @param id Station id
	 * @return Station
	 */
	Station findById(long id);

	/***
	 * retrieve Station entity by its name
	 * @param name Station name
	 * @return Station
	 */
	Station findByName(String name);

	/***
	 * produce list of StationScheduleDto what represent schedule for desired Station on desired date
	 * @param name Station name
	 * @param date desired date
	 * @return schedule represented as list of StationScheduleDto
	 */
	List<StationScheduleDto> getScheduleOfStation(String name, LocalDate date);

	/***
	 * check if Station with requested name exists in DB
	 * @param name Station name
	 * @return Station
	 */
	boolean isExist(String name);

	/***
	 * save new Station in DB
	 * @param name Station name
	 */
	void save(String name);

	/***
	 * update presented Station in DB
	 * @param station Station
	 */
	void update(Station station);

	void setStationDao(StationDao stationDao);

	void setTripService(TripService tripService);
}
