package com.edu.test.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.demo.entity.Booking;

@FeignClient(name = "BookingService")
public interface BookingFeignClient {

	
	@GetMapping("/user/{userId}")
	public List<Booking> getBookingsByUser(
            @PathVariable("userId") Integer userId);
	
	
}
