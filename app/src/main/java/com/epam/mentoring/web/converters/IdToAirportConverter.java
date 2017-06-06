package com.epam.mentoring.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.epam.mentoring.dao.repository.AirportRepository;
import com.epam.mentoring.model.Airport;

@Component
public class IdToAirportConverter implements Converter<Object, Airport> {

	@Autowired
	private AirportRepository repo;

	public Airport convert(Object element) {
		Long id = Long.parseLong((String) element);
		return repo.findOne(id);
	}
}