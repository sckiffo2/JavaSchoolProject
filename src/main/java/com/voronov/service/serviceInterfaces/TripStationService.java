package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripStationDao;
import com.voronov.entities.TripStation;

import java.util.List;

public interface TripStationService {
	/***
	 * retrieve all TripStation entities
	 * @return list of all tripStations
	 */
	List<TripStation> findAll();
	/***
	 * find and retrieve TripStation entity by id
	 * @param id TripStation id
	 * @return TripStation entity
	 */
	TripStation findById(long id);

	/***
	 * create and save new TripStation in DB
	 * @param tripStation TripStation to save
	 */
	void save(TripStation tripStation);

	/***
	 * update presented TripStation in DB
	 * @param tripStation TripStation to update
	 */
	void update(TripStation tripStation);

	/***
	 * delete TripStation by its id
	 * @param id TripStation id
	 */
	void delete(int id);

	void setTripStationDao(TripStationDao tripStationDao);
}
