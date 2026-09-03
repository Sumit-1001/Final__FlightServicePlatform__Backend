package com.edu.test.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edu.test.dto.BookingDTO;
import com.edu.test.dto.BookingRequestDTO;
import com.edu.test.dto.BookingResponseDTO;



@FeignClient(name = "BookingService")
public interface BookingFeignClient {

	
	@GetMapping("/bookings/user/{userId}")
	public List<BookingDTO> getBookingsByUser(
            @PathVariable("userId") Integer userId);
	
		
	
	@PostMapping("/bookings")
	BookingResponseDTO createBooking(
	@RequestBody BookingRequestDTO request);
	
}
