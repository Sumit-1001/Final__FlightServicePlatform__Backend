package com.flight.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUser(
            UserNotFoundException ex){

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<String> handleSchedule(
            ScheduleNotFoundException ex){

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<String> handleSeat(
            SeatNotAvailableException ex){

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String>handleBooking(BookingNotFoundException ex){
    	
    	return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}