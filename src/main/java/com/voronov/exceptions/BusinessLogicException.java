package com.voronov.exceptions;

public class BusinessLogicException extends RuntimeException {
	public BusinessLogicException(String errorMessage) {
		super(errorMessage);
	}

}
