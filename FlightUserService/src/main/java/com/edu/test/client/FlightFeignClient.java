package com.edu.test.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.test.dto.FlightDTO;
import com.edu.test.enums.DestinationLocation;
import com.edu.test.enums.SourceLocation;
@FeignClient(name = "AdminServices-1")
public interface FlightFeignClient {

    @GetMapping("/flights/allFlights")
    List<FlightDTO> getAllFlights();

    @GetMapping("/flights/{flightId}")
    FlightDTO getFlightById(
            @PathVariable("flightId") Integer flightId);

    @GetMapping("/flights/source/{source}")
    List<FlightDTO> getFlightsBySource(
            @PathVariable("source") SourceLocation source);

    @GetMapping("/flights/destination/{destination}")
    List<FlightDTO> getFlightsByDestination(
            @PathVariable("destination") DestinationLocation destination);

    @GetMapping("/flights/search/{source}/{destination}")
    List<FlightDTO> getFlightsBySourceAndDestination(
            @PathVariable("source") SourceLocation source,
            @PathVariable("destination") DestinationLocation destination);
}