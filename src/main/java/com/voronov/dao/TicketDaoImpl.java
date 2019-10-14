package com.voronov.dao;

import com.voronov.entities.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketDaoImpl implements TicketDao{
	@Override
	public Ticket findById(int id) {
		//todo implement
		return null;
	}

	@Override
	public Ticket findByName(String name) {
		return null;
	}

	@Override
	public List<Ticket> findAll() {
		return null;
	}

	@Override
	public void save(Ticket ticket) {

	}

	@Override
	public void update(Ticket ticket) {

	}

	@Override
	public void delete(Ticket id) {

	}
}
