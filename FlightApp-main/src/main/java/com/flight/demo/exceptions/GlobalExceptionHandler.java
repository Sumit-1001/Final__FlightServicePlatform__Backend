package com.flight.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flight.demo.entity.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFlightNotFoundException(
			FlightNotFoundException ex){
		
		
		ErrorResponse error = new ErrorResponse(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		
		return new ResponseEntity<>(
				error,HttpStatus.BAD_REQUEST);				
	}
	
	@ExceptionHandler(FlightAlreadyExisitsException.class)
	public ResponseEntity<ErrorResponse> handleFlightAlreadyExistsException(
			FlightAlreadyExisitsException ex) {
		
		ErrorResponse error = new ErrorResponse(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(
				error,HttpStatus.BAD_REQUEST);
	}
	
}
