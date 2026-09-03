package com.flight.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import com.flight.demo.dto.ScheduleDTO;
import com.flight.demo.security.FeignSecurityConfig;

@FeignClient(name = "FlightSchduleService",
configuration = FeignSecurityConfig.class)
public interface ScheduleFeignClient {

	@GetMapping("/api/public/schedules/{id}")
	ScheduleDTO getScheduleById(@PathVariable("id") Integer id);
	
	@PutMapping("/admin2/schedules/seats/reduce/{id}/{count}")
	String updateAvailableSeats(@PathVariable("id") Integer id, @PathVariable("count") Integer count);
	
	@PutMapping("/admin2/schedules/seats/add/{id}/{count}")
	String addSeats(@PathVariable("id") Integer id, @PathVariable("count")Integer count);
}
