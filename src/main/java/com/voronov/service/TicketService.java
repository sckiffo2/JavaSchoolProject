package com.voronov.service;

import com.voronov.entities.Ticket;

import java.util.List;

public interface TicketService {

	Ticket findById(int id);

	List<Ticket> findByTripId(int id);

	void bookTicket(Ticket ticket);

	void buyTicket(Ticket ticket);

	void delete(int id);

	List<Ticket> findAll();
}
