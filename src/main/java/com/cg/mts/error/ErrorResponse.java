package com.cg.mts.error;

public class ErrorResponse {
	
	//Fields
	private int status;
	private String message;
	private long timeStamp;
	
	//Default Constructor
	public ErrorResponse() {}

	//Parameterized Constructor
	public ErrorResponse(int status, String message, long timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	//Getters
	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	//Setters
	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	//toString() method to print the object
	@Override
	public String toString() {
		return "ScreenErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

}
