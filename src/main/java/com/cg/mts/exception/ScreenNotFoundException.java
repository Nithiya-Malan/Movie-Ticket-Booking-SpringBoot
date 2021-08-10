package com.cg.mts.exception;

public class ScreenNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	//Default
	public ScreenNotFoundException() {
		super();
	}
	
	//Message
	public ScreenNotFoundException(String message) {
		super(message);
	}
	
	//Cause
	public ScreenNotFoundException(Throwable cause) {
		super(cause);
	}

}
