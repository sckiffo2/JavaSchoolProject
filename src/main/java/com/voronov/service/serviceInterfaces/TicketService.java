package com.voronov.service.serviceInterfaces;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.Ticket;

import java.util.List;

public interface TicketService {

	void setTicketDao(TicketDao ticketDao);

	Ticket findById(int id);

	List<Ticket> findByTripId(int id);

	void bookTicket(Ticket ticket);

	void buyTicket(Ticket ticket);

	void delete(int id);

	List<Ticket> findAll();
}
