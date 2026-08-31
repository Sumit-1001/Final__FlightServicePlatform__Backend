package com.edu.test.dto;

import com.edu.test.entity.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotEmpty(message = "Username is required")
    private String userName;

    @Email(message = "Invalid email format")
    private String email;

	@Digits(integer = 10, fraction = 0)
	private long phoneNumber;

    @Valid
    private Address permanentAddress;
}