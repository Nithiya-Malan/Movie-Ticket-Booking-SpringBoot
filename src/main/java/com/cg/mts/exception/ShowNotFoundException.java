package com.cg.mts.exception;

public class ShowNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ShowNotFoundException() {
		super();
	}
	
	public ShowNotFoundException(String message) {
		super(message);
	}
	
	public ShowNotFoundException(Throwable cause) {
		super(cause);
	}
}
