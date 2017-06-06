package com.epam.mentoring.web.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateToStringConverter implements Converter<String, Date> {

	public Date convert(String element) {
		try {
			return new SimpleDateFormat("yyyy-mm-dd").parse(((String) element));
		} catch (ParseException e) {
			throw new RuntimeException("Wrong Date format");
		}
	}

}