package com.edu.test.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalUserException {
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> errorOccured1(UserNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse(ex.getMessage(), LocalDate.now());
		
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> errorOccured2(UserAlreadyExistsException ex)
	{
		ErrorResponse err = new ErrorResponse(ex.getMessage(), LocalDate.now());
		
		return new ResponseEntity<ErrorResponse>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(
			ScheduleNotFoundException.class)
			public ResponseEntity<ErrorResponse>handleScheduleNotFound(
			ScheduleNotFoundException ex) {
			
			ErrorResponse error =new ErrorResponse(ex.getMessage(), LocalDate.now());
		
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
			}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
