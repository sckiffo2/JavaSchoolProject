package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.TripStation;

import java.util.List;

public interface TripStationDao {

	TripStation findById(long id);

	List<TripStation> findAll();

	void save(TripStation tripStation);

	void update(TripStation tripStation);

	void delete(TripStation tripStation);
}
