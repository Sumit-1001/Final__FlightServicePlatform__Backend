package com.edu.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

import com.edu.test.dto.BookingDTO;
import com.edu.test.dto.FlightDTO;
import com.edu.test.dto.ScheduleDTO;
import com.edu.test.dto.UserRequestDTO;
import com.edu.test.entity.Address;
import com.edu.test.entity.User;
import com.edu.test.enums.DestinationLocation;
import com.edu.test.enums.SourceLocation;
import com.edu.test.exception.ScheduleNotFoundException;
import com.edu.test.exception.UserAlreadyExistsException;
import com.edu.test.exception.UserNotFoundException;

@Service
public interface IUserService  {

	
	public String addNewUser(UserRequestDTO user) throws UserAlreadyExistsException;
	
	public List<User> getAllUser();
	
	public User findByUserId(int UserId)throws UserNotFoundException;
	
	public User findByemail(String email)throws UserNotFoundException;
	
	public User findByphoneNumber(long phoneNumber)throws UserNotFoundException;
	
	public User findByuserName(String userName)throws UserNotFoundException;
	
	public String UpdateUserByUserId(User user ,int UserId)throws UserNotFoundException;
	
	public String DeleteByUserId(int UserId)throws UserNotFoundException;
	
	public String addUserAddress(Address userAddress ,int UserId)throws UserNotFoundException;


	    List<FlightDTO> getAllFlights();

	    FlightDTO getFlightById(Integer flightId);

	    List<FlightDTO> getFlightsBySource(SourceLocation source);

	    List<FlightDTO> getFlightsByDestination(DestinationLocation destination);

	    List<FlightDTO> getFlightsBySourceAndDestination(
	            SourceLocation source,
	            DestinationLocation destination);
	    
	    
	    
	    // booking check by id
	    
	    List<BookingDTO> getBookingsByUserId(Integer userId) throws UserNotFoundException;
	    
	    //find by scheduleid
	    ScheduleDTO getScheduleById(int id)throws ScheduleNotFoundException ;
	    
	}
