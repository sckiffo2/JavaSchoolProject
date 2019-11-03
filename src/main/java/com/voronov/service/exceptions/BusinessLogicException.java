package com.voronov.service.exceptions;

public class BusinessLogicException extends RuntimeException {
	public BusinessLogicException(String errorMessage) {
		super(errorMessage);
	}

}
