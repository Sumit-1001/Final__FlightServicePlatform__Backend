package com.edu.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.test.dto.ScheduleDTO;
import com.edu.test.exception.ScheduleNotFoundException;


@FeignClient(name = "FlightSchduleService")
public interface ScheduleFeignClient {

	    @GetMapping("/api/public/schedules/{id}")
	    ScheduleDTO getScheduleById(
	            @PathVariable("id") int id) throws ScheduleNotFoundException ;
	}

