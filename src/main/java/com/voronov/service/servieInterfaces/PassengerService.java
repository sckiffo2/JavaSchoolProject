package com.voronov.service.servieInterfaces;

import com.voronov.dao.DAOinterfaces.PassengerDao;
import com.voronov.entities.Passenger;

import java.time.LocalDate;
import java.util.List;

public interface PassengerService {

	void setPassengerDao(PassengerDao passengerDao);

	Passenger findById(int id);

	Passenger findByName(String firstName, String lastName, LocalDate birthDate);

	List<Passenger> findPassengersOnTrip(int id);

	void save(Passenger passenger);

	void update(Passenger passenger);

	void delete(int id);

	List<Passenger> findAll();
}
