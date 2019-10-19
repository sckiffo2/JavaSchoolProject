package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Passenger;

import java.util.List;

public interface PassengerDao {
	Passenger findById(int id);

	Passenger findByName(String name);

	List<Passenger> findPassengersOnTrip(int id);

	void save(Passenger passenger);

	void update(Passenger passenger);

	void delete(Passenger passenger);

	@SuppressWarnings("unchecked")
	List<Passenger> findAll();
}
