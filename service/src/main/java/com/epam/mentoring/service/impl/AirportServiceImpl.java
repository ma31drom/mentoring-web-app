package com.epam.mentoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.dao.repository.AirportRepository;
import com.epam.mentoring.model.Airport;
import com.epam.mentoring.service.AirportService;

public class AirportServiceImpl extends AbstractCrudService<Airport> implements AirportService {

	@Autowired
	private AirportRepository repo;

	@Override
	protected JpaRepository<Airport, Long> getRepo() {
		return repo;
	}

}
