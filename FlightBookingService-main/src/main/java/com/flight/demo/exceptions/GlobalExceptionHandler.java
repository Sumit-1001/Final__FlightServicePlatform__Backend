package com.flight.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUser(
            UserNotFoundException ex){
ErrorResponse err = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSchedule(
            ScheduleNotFoundException ex){

ErrorResponse err = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleSeat(
            SeatNotAvailableException ex){

ErrorResponse err = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleBooking(BookingNotFoundException ex){
    	
ErrorResponse err = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.NOT_FOUND);
		}
}