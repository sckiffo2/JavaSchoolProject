package com.voronov.dao;

import com.voronov.entities.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerDaoImpl implements PassengerDao{
	@Override
	public Passenger findById(int id) {
		//todo implement
		return null;
	}

	@Override
	public Passenger findByName(String name) {
		return null;
	}

	@Override
	public List<Passenger> findAll() {
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
}
