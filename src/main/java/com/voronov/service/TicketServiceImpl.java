package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.Ticket;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Override
	public Ticket findById(int id) {
		return ticketDao.findById(id);
	}

	@Override
	public List<Ticket> findByTripId(int id) {
		return ticketDao.findByTripId(id);
	}

	@Override
	public void bookTicket(Ticket ticket) {
		ticketDao.save(ticket);
	}

	@Override
	public void buyTicket(Ticket ticket) {
		ticketDao.update(ticket);
	}

	@Override
	public void delete(int id) {
		Ticket deleteTicket = ticketDao.findById(id);
		ticketDao.delete(deleteTicket);
	}

	@Override
	public List<Ticket> findAll() {
		return ticketDao.findAll();
	}
}