package com.epam.mentoring.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.epam.mentoring.model.Airport;

@Component
public class AirportToStringConverter implements Converter<Airport, String> {

	public String convert(Airport element) {
		return element.getName();
	}

}