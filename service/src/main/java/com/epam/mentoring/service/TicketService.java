package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Ticket;
import com.epam.mentoring.model.User;

public interface TicketService extends CrudService<Ticket> {

	List<Ticket> getUserTickets(User user);

	List<Ticket> getTicketsForFlight(Flight flight);

}
