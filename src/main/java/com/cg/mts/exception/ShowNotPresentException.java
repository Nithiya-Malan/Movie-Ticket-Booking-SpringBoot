package com.cg.mts.exception;

public class ShowNotPresentException extends Exception{

	private static final long serialVersionUID = 1L;

	public ShowNotPresentException() {
		super();
	}
	
	public ShowNotPresentException(String message) {
		super(message);
	}
	
	public ShowNotPresentException(Throwable cause) {
		super(cause);
	}

}
