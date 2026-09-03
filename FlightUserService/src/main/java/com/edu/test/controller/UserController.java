package com.edu.test.controller;

import java.util.List;

import org.apache.hc.client5.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.test.client.ScheduleFeignClient;
import com.edu.test.dto.BookingDTO;
import com.edu.test.dto.BookingRequestDTO;
import com.edu.test.dto.BookingResponseDTO;
import com.edu.test.dto.FlightDTO;
import com.edu.test.dto.ScheduleDTO;
import com.edu.test.dto.UserLoginDTO;
import com.edu.test.dto.UserRequestDTO;
import com.edu.test.entity.Address;
import com.edu.test.entity.User;
import com.edu.test.enums.DestinationLocation;
import com.edu.test.enums.SourceLocation;
import com.edu.test.exception.ScheduleNotFoundException;
import com.edu.test.exception.UserAlreadyExistsException;
import com.edu.test.exception.UserNotFoundException;
import com.edu.test.repository.IUserRepository;
import com.edu.test.service.IUserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public")
public class UserController {

	@Autowired
	IUserService userService;
	
	
	
	public UserController() {
		System.out.println("Course user Constructor called ");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(
	@RequestBody UserLoginDTO dto)  throws UserNotFoundException , InvalidCredentialsException{
	return ResponseEntity.ok(
	userService.login(dto));
	}
	
	@PostMapping("/addUser")
	@Transactional
	public ResponseEntity<String> addNewUser(@Valid @RequestBody UserRequestDTO user)throws UserAlreadyExistsException
	{
		String Notification = userService.addNewUser(user);
				
		return new ResponseEntity<String>(Notification,HttpStatus.OK);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> allUsers = userService.getAllUser();
				
		return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
	}
	
	
	@GetMapping("/User/id/{UserId}")
	public ResponseEntity<User> findByUserId(@Valid @PathVariable(required = true) int UserId)throws UserNotFoundException
	{
		User user1 = userService.findByUserId(UserId);
				
		return new ResponseEntity<User>(user1,HttpStatus.OK);
	}
	
	@GetMapping("/User/email/{email}")
	public ResponseEntity<User> findByemail(@Valid @PathVariable(required = true) String email)throws UserNotFoundException
	{
		User user1 = userService.findByemail(email);
				
		return new ResponseEntity<User>(user1,HttpStatus.OK);
	}
	
	@GetMapping("/User/pnumber/{phoneNumber}")
	public ResponseEntity<User> findByphoneNumber(@Valid @PathVariable(required = true) long phoneNumber)throws UserNotFoundException
	{
		User user1 = userService.findByphoneNumber(phoneNumber);
				
		return new ResponseEntity<User>(user1,HttpStatus.OK);
	}
	
	@GetMapping("/User/name/{userName}")
	public ResponseEntity<User> findByuserName(@Valid @PathVariable(required = true) String userName)throws UserNotFoundException
	{
		User user1 = userService.findByuserName(userName);
				
		return new ResponseEntity<User>(user1,HttpStatus.OK);
	}
	
	
	@PutMapping("/UpdateUser")
	@Transactional
	public ResponseEntity<String> UpdateUserByUserId(@Valid @RequestBody User user ,@RequestParam(required = true) int UserId)throws UserNotFoundException
	{
		String Notification = userService.UpdateUserByUserId(user , UserId);
				
		return new ResponseEntity<String>(Notification,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/DeleteUser")
	@Transactional
	public ResponseEntity<String> DeleteByUserId(@Valid @RequestParam(required = true) int UserId)throws UserNotFoundException
	{
		String Notification = userService.DeleteByUserId(UserId);
				
		return new ResponseEntity<String>(Notification,HttpStatus.OK);
	}
	
	
	@PostMapping("/addUserAddress")
	@Transactional
	public ResponseEntity<String> addUserAddress(@Valid @RequestBody Address userAddress ,@RequestParam(required = true) int UserId)throws UserNotFoundException
	{
		String Notification = userService.addUserAddress(userAddress , UserId);
				
		return new ResponseEntity<String>(Notification,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/flights")
	public ResponseEntity<List<FlightDTO>> getAllFlights() {
	    return ResponseEntity.ok(userService.getAllFlights());
	}

	@GetMapping("/flights/{flightId}")
	public ResponseEntity<FlightDTO> getFlightById(
	        @PathVariable Integer flightId) {

	    return ResponseEntity.ok(
	            userService.getFlightById(flightId));
	}

	@GetMapping("/flights/source/{source}")
	public ResponseEntity<List<FlightDTO>> getFlightsBySource(
	        @PathVariable SourceLocation source) {

	    return ResponseEntity.ok(
	            userService.getFlightsBySource(source));
	}

	@GetMapping("/flights/destination/{destination}")
	public ResponseEntity<List<FlightDTO>> getFlightsByDestination(
	        @PathVariable DestinationLocation destination) {

	    return ResponseEntity.ok(
	            userService.getFlightsByDestination(destination));
	}

	@GetMapping("/flights/search/{source}/{destination}")
	public ResponseEntity<List<FlightDTO>> getFlightsBySourceAndDestination(
	        @PathVariable SourceLocation source,
	        @PathVariable DestinationLocation destination) {

	    return ResponseEntity.ok(
	            userService.getFlightsBySourceAndDestination(
	                    source,
	                    destination));
	}
	
	@GetMapping("/bookings/user/{userId}")
	public List<BookingDTO> getBookingsByUserId(
	@PathVariable Integer userId)  throws UserNotFoundException{
	return userService.getBookingsByUserId(userId);
	}
	

	
	@PostMapping("/bookings")
	public ResponseEntity<BookingResponseDTO> createBooking(
	@RequestBody BookingRequestDTO request) {
	return new ResponseEntity<>(
	userService.createBooking(request),
	HttpStatus.CREATED);
	}
	
	@GetMapping("/schedules/{id}")
	public ResponseEntity<ScheduleDTO> getScheduleById(
	        @PathVariable int id)
	        throws ScheduleNotFoundException {

	    return ResponseEntity.ok(
	            userService.getScheduleById(id));
	}
}
