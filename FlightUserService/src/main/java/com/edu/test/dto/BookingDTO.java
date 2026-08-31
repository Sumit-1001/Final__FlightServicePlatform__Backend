package com.edu.test.dto;


import java.time.LocalDate;

import com.edu.test.enums.BookingStatus;
import lombok.Data;

@Data
public class BookingDTO {
	

	private Integer bookingId;
	
	private String userName;
	
	private Integer scheduleId;
	
	private Integer seatsBooked;
	
	private Double totalFare;
	
	private BookingStatus bookingStatus;
	
	private LocalDate bookingDate;	}

