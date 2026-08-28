package com.edu.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.test.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	
	
	public User findByemail(String email);
	
	public User findByphoneNumber(long phoneNumber);
	
	public User findByuserName(String userName);
	


}
