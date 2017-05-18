package com.epam.mentoring.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findByStartAirport(Airport start);

	List<Flight> findByFinishAirport(Airport finish);

	List<Flight> findByStartDateBetween(Date start, Date end);
}
