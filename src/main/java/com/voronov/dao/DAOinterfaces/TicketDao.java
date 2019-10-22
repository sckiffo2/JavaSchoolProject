package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Ticket;

import java.util.List;

public interface TicketDao {
	Ticket findById(long id);

	@SuppressWarnings("unchecked")
	List<Ticket> findAll();

	public List<Ticket> findByTripId(long id);

	void save(Ticket ticket);

	void update(Ticket ticket);

	void delete(Ticket ticket);
}
