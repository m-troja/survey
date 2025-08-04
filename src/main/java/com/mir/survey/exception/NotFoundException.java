package com.mir.survey.exception;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException(String username) {
		super("Not found :)" ); //  parse message to Throwable
	}
	
	
}
