package com.edu.test.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAlreadyExistsException extends Exception {

	private String val;

	public UserAlreadyExistsException(String val) {
		super(val);
		this.val = val;
	}

	@Override
	public String toString() {
		return "UserAlreadyExistsException [val=" + val + "]";
	}
	
	
}
