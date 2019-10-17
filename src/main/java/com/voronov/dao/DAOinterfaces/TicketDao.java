package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Ticket;

import java.util.List;

public interface TicketDao {
	Ticket findById(int id);

	Ticket findByName(String name);

	@SuppressWarnings("unchecked")
	List<Ticket> findAll();

	void save(Ticket ticket);

	void update(Ticket ticket);

	void delete(Ticket id);
}
