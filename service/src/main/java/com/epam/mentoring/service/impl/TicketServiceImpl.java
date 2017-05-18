package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.dao.repository.FlightRepository;
import com.epam.mentoring.dao.repository.TicketRepository;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Ticket;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.TicketService;

@Service
public class TicketServiceImpl extends AbstractCrudService<Ticket> implements TicketService {

	@Autowired
	private TicketRepository ticketRepo;

	@Autowired
	private FlightRepository flightRepo;

	@Transactional
	@Override
	public void save(final Ticket ticket) {
		Flight flight = ticket.getFlight();
		flight.setAvailableTicketCount(flight.getAvailableTicketCount() - 1);
		flightRepo.save(flight);
		ticket.setCost(flight.getCost());
		ticketRepo.save(ticket);
	}

	@Override
	public List<Ticket> getUserTickets(User user) {
		return ticketRepo.findByUser(user);
	}

	@Override
	public List<Ticket> getTicketsForFlight(Flight flight) {
		return ticketRepo.findByFlight(flight);
	}

	@Override
	protected JpaRepository<Ticket, Long> getRepo() {
		return ticketRepo;
	}

}
