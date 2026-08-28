package com.flight.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<String> handleFlightNotFoundException(
			FlightNotFoundException ex){
		return new ResponseEntity<>(
				ex.getMessage(),HttpStatus.BAD_REQUEST);				
	}
	
	@ExceptionHandler(FlightAlreadyExisitsException.class)
	public ResponseEntity<String> handleFlightAlreadyExistsException(
			FlightAlreadyExisitsException ex) {
		return new ResponseEntity<>(
				ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}
