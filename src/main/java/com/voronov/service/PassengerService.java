package com.voronov.service;

import com.voronov.dao.PassengerDao;
import com.voronov.entities.Passenger;

import java.util.List;

public interface PassengerService {
	void setPassengerDao(PassengerDao stationDao);

	Passenger findById(int id);

	Passenger findByName(String name);

	void save(Passenger passenger);

	void update(Passenger passenger);

	void delete(Integer id);

	List<Passenger> findAll();
}
