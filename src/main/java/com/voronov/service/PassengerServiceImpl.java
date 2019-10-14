package com.voronov.service;

import com.voronov.dao.PassengerDao;
import com.voronov.dao.PassengerDaoImpl;
import com.voronov.entities.Passenger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PassengerServiceImpl implements PassengerService{

	private PassengerDao passengerDao;


	@Autowired
	@Override
	public void setPassengerDao(PassengerDao stationDao) {
		this.passengerDao = passengerDao;
	}

	@Override
	public Passenger findById(int id) {
		return null;
	}

	@Override
	public Passenger findByName(String name) {
		return null;
	}

	@Override
	public void save(Passenger passenger) {

	}

	@Override
	public void update(Passenger passenger) {

	}

	@Override
	public void delete(Integer id) {

	}

	@Override
	public List<Passenger> findAll() {
		return null;
	}
}
