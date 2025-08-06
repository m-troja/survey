package com.mir.survey.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String username) {
		super("Not found :)" ); //  parse message to Throwable
	}


}
