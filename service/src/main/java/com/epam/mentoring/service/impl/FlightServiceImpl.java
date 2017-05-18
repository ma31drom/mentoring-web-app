package com.epam.mentoring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.repository.FlightRepository;
import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.service.FlightService;

@Service
public class FlightServiceImpl extends AbstractCrudService<Flight> implements FlightService {

	@Autowired
	private FlightRepository repo;

	@Override
	public List<Flight> getFlightsByDateRange(final Date after, final Date before) {
		return repo.findByStartDateBetween(after, before);
	}

	@Override
	public void save(final Flight flight) {
		flight.setAvailableTicketCount(flight.getFullTicketCount());
		repo.save(flight);
	}

	@Override
	public List<Flight> getFlightsByStartAirport(Airport endpoint) {
		return repo.findByStartAirport(endpoint);
	}

	@Override
	public List<Flight> getFlightsByFinishAirport(Airport endpoint) {
		return repo.findByFinishAirport(endpoint);
	}

	@Override
	protected JpaRepository<Flight, Long> getRepo() {
		return repo;
	}
}
