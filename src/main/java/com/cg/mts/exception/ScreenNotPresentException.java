package com.cg.mts.exception;

public class ScreenNotPresentException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ScreenNotPresentException() {
		super();
	}
	
	public ScreenNotPresentException(String message) {
		super(message);
	}
	
	public ScreenNotPresentException(Throwable cause) {
		super(cause);
	}
}
