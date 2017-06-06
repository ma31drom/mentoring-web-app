package com.epam.mentoring.web.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDateConverter implements Converter<Date, String> {

	public String convert(Date date) {
		return new SimpleDateFormat("yyyy-mm-dd").format(date);
	}

}