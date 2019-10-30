package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TripStationDao;
import com.voronov.entities.TripStation;

import java.util.List;

public interface TripStationService {

	TripStation findById(long id);

	void save(TripStation tripStation);

	void update(TripStation tripStation);

	void delete(int id);

	List<TripStation> findAll();

	void setTripStationDao(TripStationDao tripStationDao);
}
