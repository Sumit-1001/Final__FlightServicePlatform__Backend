package com.flight.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

public class ErrorResponse {
	private String message;
	private int status;
	private LocalDateTime timestamp;
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(String message, int status, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	

}
