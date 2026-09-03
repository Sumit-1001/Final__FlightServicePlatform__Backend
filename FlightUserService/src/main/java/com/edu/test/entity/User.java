package com.edu.test.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int UserId;
	@NotEmpty
	private String userName;
	
	@Column(unique = true)
	private String email;
	
	@Digits(integer = 10, fraction = 0)
	private long phoneNumber;
	
	@Column
	private String password = "user123";
	
	
	@Embedded
	private Address permanentAddress;


	public User(@NotEmpty String userName, String email, @Digits(integer = 10, fraction = 0) long phoneNumber,
			Address permanentAddress) {
		super();
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.permanentAddress = permanentAddress;
	}

	

	


	
	
	
	
}
