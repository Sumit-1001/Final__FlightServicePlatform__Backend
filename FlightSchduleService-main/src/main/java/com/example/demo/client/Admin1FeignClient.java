package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.security.FeignConfig;
import com.example.demo.dto.FlightDTO;

@FeignClient(
        name = "ADMINSERVICES-1",
        configuration = FeignConfig.class
)
public interface Admin1FeignClient {

    @GetMapping("/flights/{flightId}")
    FlightDTO getFlightById(@PathVariable int flightId);
}