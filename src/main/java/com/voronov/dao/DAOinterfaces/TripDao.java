package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.TrainTrip;

import java.util.List;

public interface TripDao {
	TrainTrip findById(int id);

	TrainTrip findByName(String name);

	@SuppressWarnings("unchecked")
	List<TrainTrip> findAll();

	void save(TrainTrip trip);

	void update(TrainTrip trip);

	void delete(TrainTrip id);
}
