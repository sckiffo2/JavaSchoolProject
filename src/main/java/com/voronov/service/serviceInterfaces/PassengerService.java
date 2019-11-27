package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.PassengerDao;
import com.voronov.entities.Passenger;

import java.time.LocalDate;
import java.util.List;

public interface PassengerService {
	/***
	 * retrieve all passenger entities
 	 * @return list of all passengers
	 */
	List<Passenger> findAll();

	/***
	 * find and retrieve passenger entity by id
	 * @param id
	 * @return Passenger
	 */
	Passenger findById(long id);

	/***
	 * find and retrieve passenger by its data
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @return Passenger
	 */
	Passenger findByPassengerData(String firstName, String lastName, LocalDate birthDate);

	/***
	 * retrieve all Rassenger entities what registered on Trip
	 * @param id
	 * @return list of Passengers on Trip
	 */
	List<Passenger> findPassengersOnTrip(long id);

	/***
	 * check if this Passenger is already registered on this Trip
	 * @param passenger
	 * @param tripId
	 * @return
	 */
	boolean isPassengerOnTrip(Passenger passenger, long tripId);

	/***
	 * save new Passenger in DB
	 * @param passenger Passenger
	 */
	void save(Passenger passenger);

	/***
	 * update Presented Passenger entity in DB
	 * @param passenger Passenger
	 */
	void update(Passenger passenger);

	/***
	 * delete Passenger by its id
	 * @param id Passenger id
	 */
	void delete(int id);

	void setPassengerDao(PassengerDao passengerDao);
}
