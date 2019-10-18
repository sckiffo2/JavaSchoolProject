package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Ticket;

import java.util.List;

public interface TicketDao {
	Ticket findById(int id);

	@SuppressWarnings("unchecked")
	List<Ticket> findAll();

	public List<Ticket> findByTripId(int id);

	void save(Ticket ticket);

	void update(Ticket ticket);

	void delete(Ticket ticket);
}
