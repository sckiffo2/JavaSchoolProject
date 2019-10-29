package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Passenger;

import java.time.LocalDate;
import java.util.List;

public interface PassengerDao {
	Passenger findById(long id);

	Passenger findByPassengerData(String firstName, String lastName, LocalDate birthDate);

	List<Passenger> findPassengersOnTrip(long id);

	void save(Passenger passenger);

	void update(Passenger passenger);

	void delete(Passenger passenger);

	@SuppressWarnings("unchecked")
	List<Passenger> findAll();
}
