package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Ticket;
import com.voronov.entities.Trip;

import java.util.List;

public interface TicketDao {
	Ticket findById(long id);

	@SuppressWarnings("unchecked")
	List<Ticket> findAll();

	public List<Ticket> findByTripId(long id);

	Ticket findTicketByTripAndPlace(long tripId, int wagon, int place);

	void save(Ticket ticket);

	void update(Ticket ticket);

	void delete(Ticket ticket);

	boolean isExists(Trip trip, int wagon, int place);
}
