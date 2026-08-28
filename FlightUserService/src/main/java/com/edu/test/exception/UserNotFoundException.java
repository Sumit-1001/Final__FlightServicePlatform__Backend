package com.edu.test.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserNotFoundException extends Exception {

	private String val;

	public UserNotFoundException(String value) {
		super(value);
		this.val = value;
	}

	@Override
	public String toString() {
		return "UserNotFoundException [val=" + val + "]";
	}
	
	
	
}
