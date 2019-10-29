package com.voronov.service;

import com.voronov.dao.DAOinterfaces.PassengerDao;
import com.voronov.entities.Passenger;
import com.voronov.service.serviceInterfaces.PassengerService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerDao passengerDao;

	@Override
	public Passenger findById(long id) {
		return passengerDao.findById(id);
	}

	@Override
	public Passenger findByPassengerData(String firstName, String lastName, LocalDate birthDate) {
		return passengerDao.findByPassengerData(firstName, lastName, birthDate);
	}

	@Override
	public List<Passenger> findPassengersOnTrip(long id) {
		return passengerDao.findPassengersOnTrip(id);
	}

	@Override
	public boolean isPassengerOnTrip(Passenger passenger, long tripId) {
		List<Passenger> passengersOnTrip = passengerDao.findPassengersOnTrip(tripId);
		return passengersOnTrip.contains(passenger);
	}

	@Override
	public void save(Passenger passenger) {
		passengerDao.save(passenger);
	}

	@Override
	public void update(Passenger passenger) {
		passengerDao.update(passenger);
	}

	@Override
	public void delete(int id) {
		Passenger deletePassenger = passengerDao.findById(id);
		passengerDao.delete(deletePassenger);
	}

	@Override
	public List<Passenger> findAll() {
		return passengerDao.findAll();
	}

}
