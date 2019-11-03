package com.voronov.controllers;


import com.voronov.service.exceptions.BusinessLogicException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
//  On this @ControllerAdvice
public class GlobalControllerExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "ErrorDefault";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView
	defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

//		if (AnnotationUtils.findAnnotation
//				(e.getClass(), ResponseStatus.class) != null)
//			throw e;

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	@ExceptionHandler(value = BusinessLogicException.class)
	public ModelAndView
	businessLogicExceptionHandler(HttpServletRequest req, Exception e) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e.getMessage());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("ErrorBusiness");
		return mav;
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ModelAndView
	illegalUserInput(HttpServletRequest req, Exception e) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e.getMessage());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("ErrorBusiness");
		return mav;
	}
}
