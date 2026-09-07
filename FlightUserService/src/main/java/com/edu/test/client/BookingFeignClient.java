package com.edu.test.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.test.dto.BookingDTO;



@FeignClient(name = "BookingService")
public interface BookingFeignClient {

	
	@GetMapping("/bookings/user/{userId}")
	public List<BookingDTO> getBookingsByUser(
            @PathVariable("userId") Integer userId);
	
	
}
