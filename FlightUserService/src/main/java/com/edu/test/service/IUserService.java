package com.edu.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

import com.edu.test.entity.Address;
import com.edu.test.entity.User;
import com.edu.test.exception.UserAlreadyExistsException;
import com.edu.test.exception.UserNotFoundException;

@Service
public interface IUserService  {

	
	public String addNewUser(User user) throws UserAlreadyExistsException;
	
	
	public List<User> getAllUser();
	
	public User findByUserId(int UserId)throws UserNotFoundException;
	
	public User findByemail(String email)throws UserNotFoundException;
	
	public User findByphoneNumber(long phoneNumber)throws UserNotFoundException;
	
	public User findByuserName(String userName)throws UserNotFoundException;
	
	public String UpdateUserByUserId(User user ,int UserId)throws UserNotFoundException;
	
	public String DeleteByUserId(int UserId)throws UserNotFoundException;
	
	public String addUserAddress(Address userAddress ,int UserId)throws UserNotFoundException;
}
