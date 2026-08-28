package com.flight.demo.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.demo.dto.UserDTO;
import com.flight.demo.security.FeignSecurityConfig;



@FeignClient(name = "FlightUserService",
configuration = FeignSecurityConfig.class)
public interface UserFeignClient {

	@GetMapping("/api/public/User/id/{UserId}")
	UserDTO getUserById(@PathVariable("UserId") Integer UserId);
}
