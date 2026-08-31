package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.entity.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(
			ScheduleNotFoundException.class)
			public ResponseEntity<ErrorResponse>handleScheduleNotFound(
			ScheduleNotFoundException ex) {
			
			ErrorResponse error =new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
			LocalDateTime.now());
		
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
			}
			
	@ExceptionHandler(
			SeatNotAvailableException.class)
	public ResponseEntity<ErrorResponse>handleScheduleNotFound(
	SeatNotAvailableException ex) {
	
	ErrorResponse error =new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
	LocalDateTime.now());

	return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(
			FlightNotFoundException.class)
	public ResponseEntity<ErrorResponse>handleScheduleNotFound(
	FlightNotFoundException ex) {
	
	ErrorResponse error =new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
	LocalDateTime.now());

	return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(
			ScheduleAlreadyExistException.class)
	public ResponseEntity<ErrorResponse>handleScheduleAlreadyExistException(
			ScheduleAlreadyExistException ex) {
	
	ErrorResponse error =new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
	LocalDateTime.now());

	return new ResponseEntity<>(error,HttpStatus.ALREADY_REPORTED);
	}
	
}
