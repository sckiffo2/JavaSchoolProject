package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.PassengerDao;
import com.voronov.entities.Passenger;

import java.time.LocalDate;
import java.util.List;

public interface PassengerService {

	void setPassengerDao(PassengerDao passengerDao);

	Passenger findById(long id);

	Passenger findByName(String firstName, String lastName, LocalDate birthDate);

	List<Passenger> findPassengersOnTrip(long id);

	void save(Passenger passenger);

	void update(Passenger passenger);

	void delete(int id);

	List<Passenger> findAll();
}
