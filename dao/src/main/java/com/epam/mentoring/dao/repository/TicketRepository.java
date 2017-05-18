package com.epam.mentoring.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Ticket;
import com.epam.mentoring.model.User;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByUser(User user);

	List<Ticket> findByFlight(Flight flight);

}