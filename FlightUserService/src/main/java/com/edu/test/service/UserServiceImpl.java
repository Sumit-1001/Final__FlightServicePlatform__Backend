package com.edu.test.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.test.client.BookingFeignClient;
import com.edu.test.client.FlightFeignClient;
import com.edu.test.client.ScheduleFeignClient;
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
import com.edu.test.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	private FlightFeignClient flightFeignClient;
	
	@Autowired
	private BookingFeignClient bookingFeignClient;
	
	@Autowired
	private ScheduleFeignClient scheduleFeignClient;
	
	@Override
	public String addNewUser(UserRequestDTO user) throws UserAlreadyExistsException {

	    boolean flag = userRepository.findAll()
	            .stream()
	            .anyMatch(c -> c.getEmail().equalsIgnoreCase(user.getEmail()));

	    if (flag) {
	        throw new UserAlreadyExistsException("User already exists !!!");
	    }

	    User newUser = new User();
	    newUser.setUserName(user.getUserName());
	    newUser.setEmail(user.getEmail());
	    newUser.setPhoneNumber(user.getPhoneNumber());
	    newUser.setPermanentAddress(user.getPermanentAddress());

	    userRepository.save(newUser);

	    return "User info saved successfully. UserId : " + newUser.getUserId();
	}

	@Override
	public List<User> getAllUser() {
     
		List<User> allUsers = userRepository.findAll().stream().toList();
		
		return allUsers;
	}

	@Override
	public User findByUserId(int UserId) throws UserNotFoundException {
		
		boolean flag = userRepository.findAll().stream().anyMatch(c -> c.getUserId()==UserId);
				
		
		if(!flag)
		{
			throw new UserNotFoundException("User doesn't exist of UserId : "+UserId);
		}
		
		User user1 = userRepository.findById(UserId).get();
		
		return user1;
	}

	@Override
	public String UpdateUserByUserId(User user, int UserId) throws UserNotFoundException {
		
        boolean flag = userRepository.findAll().stream().anyMatch(c -> c.getUserId()==UserId);
				
		
		if(!flag)
		{
			throw new UserNotFoundException("User doesn't exist of UserId : "+UserId);
		}
		
		User user1 =userRepository.findById(UserId).get();
		
		user1.setUserName(user.getUserName());
		user1.setEmail(user.getEmail());
		user1.setPhoneNumber(user.getPhoneNumber());
		user1.setPermanentAddress(user.getPermanentAddress());
		
		
		return "user info updated for userid : "+UserId;
		
	}

	@Override
	public String DeleteByUserId(int UserId) throws UserNotFoundException {
		
        boolean flag = userRepository.findAll().stream().anyMatch(c -> c.getUserId()==UserId);
				

		if(!flag)
		{
			throw new UserNotFoundException("User doesn't exist of UserId : "+UserId);
		}
		
		userRepository.deleteById(UserId);
		
		return "User is removed from the Database";
	}

	@Override
	public String addUserAddress(Address userAddress ,int UserId) throws UserNotFoundException{
       boolean flag = userRepository.findAll().stream().anyMatch(c -> c.getUserId()==UserId);
       
		if(!flag)
		{
			throw new UserNotFoundException("User doesn't exist of UserId : "+UserId);
		}
		
		User user1 =userRepository.findById(UserId).get();
		
		user1.setPermanentAddress(userAddress);
		
		return "address saved for userid : "+UserId;
	}

	@Override
	public User findByemail(String email) throws UserNotFoundException {
        boolean flag = userRepository.findAll().stream().anyMatch(c -> c.getEmail().equals(email));
				
		
		if(!flag)
		{
			throw new UserNotFoundException("User doesn't exist of email : "+email);
		}
		
        User user1 = userRepository.findByemail(email);
		
		return user1;
	}

	@Override
	public User findByphoneNumber(long phoneNumber) throws UserNotFoundException {
        boolean flag = userRepository.findAll().stream().anyMatch(c -> c.getPhoneNumber()==phoneNumber);
				
		
		if(!flag)
		{
			throw new UserNotFoundException("User doesn't exist of phoneNumber : "+phoneNumber);
		}
		
        User user1 = userRepository.findByphoneNumber(phoneNumber);
		
		return user1;
	}

	@Override
	public User findByuserName(String userName) throws UserNotFoundException {
		boolean flag = userRepository.findAll().stream().anyMatch(c -> c.getUserName().equalsIgnoreCase(userName));
		
		

		if(!flag)
		{
			throw new UserNotFoundException("User doesn't exist of userName : "+userName);
		}
		
        User user1 = userRepository.findByuserName(userName);
		
		return user1;
	}
	@Override
	public List<FlightDTO> getAllFlights() {
	    return flightFeignClient.getAllFlights();
	}

	@Override
	public FlightDTO getFlightById(Integer flightId) {
	    return flightFeignClient.getFlightById(flightId);
	}

	@Override
	public List<FlightDTO> getFlightsBySource(SourceLocation source) {
	    return flightFeignClient.getFlightsBySource(source);
	}

	@Override
	public List<FlightDTO> getFlightsByDestination(
	        DestinationLocation destination) {

	    return flightFeignClient.getFlightsByDestination(destination);
	}

	@Override
	public List<FlightDTO> getFlightsBySourceAndDestination(
	        SourceLocation source,
	        DestinationLocation destination) {

	    return flightFeignClient
	            .getFlightsBySourceAndDestination(
	                    source,
	                    destination);
	}
	
	
	
	// booking get by userid
	
	@Override
	public List<BookingDTO> getBookingsByUserId(Integer userId) throws UserNotFoundException {
		User user11 = userRepository.findById(userId)
		        .orElseThrow(() ->
		                new UserNotFoundException(
		                        "User doesn't exist of UserId : " + userId));
		
		
	List<BookingDTO> userDetailsList = bookingFeignClient.getBookingsByUser(userId);
	
	userDetailsList.forEach(b ->
	b.setUserName(user11.getUserName()));
	return userDetailsList;
	}
	
	@Override
	public ScheduleDTO getScheduleById(int id)
	throws ScheduleNotFoundException {
	return scheduleFeignClient.getScheduleById(id);
	}
	
	
	
	
}
