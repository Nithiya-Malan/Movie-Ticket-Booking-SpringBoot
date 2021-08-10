package com.cg.mts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.mts.error.ErrorResponse;

@ControllerAdvice
public class ShowExceptionHandler {
	
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(ShowNotFoundException exception){
			ErrorResponse error = new ErrorResponse();
			
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exception.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(ShowNotPresentException exception){
			ErrorResponse error = new ErrorResponse();
			
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exception.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}

}
