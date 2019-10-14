package com.voronov.dao;

import com.voronov.entities.Passenger;

import java.util.List;

public interface PassengerDao {
	Passenger findById(int id);

	Passenger findByName(String name);

	@SuppressWarnings("unchecked")
	List<Passenger> findAll();

	void save(Passenger passenger);

	void update(Passenger passenger);

	void delete(Integer id);
}
