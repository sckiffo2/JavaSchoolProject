package com.voronov.dao;

import com.voronov.entities.TrainTrip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripDaoImpl implements TripDao{
	@Override
	public TrainTrip findById(int id) {
		//todo implement
		return null;
	}

	@Override
	public TrainTrip findByName(String name) {
		return null;
	}

	@Override
	public List<TrainTrip> findAll() {
		return null;
	}

	@Override
	public void save(TrainTrip trip) {

	}

	@Override
	public void update(TrainTrip trip) {

	}

	@Override
	public void delete(TrainTrip id) {

	}
}
