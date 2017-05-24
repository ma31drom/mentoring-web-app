package com.epam.mentoring.web.controller.html;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackageClasses = { DefaultExceptionHandler.class })
public class DefaultExceptionHandler {

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public ModelAndView handleError(Exception e) {
		ModelAndView mav = new ModelAndView("redirect:/home");
		mav.addObject("errorMessage", e.getMessage());
		return mav;
	}
}
