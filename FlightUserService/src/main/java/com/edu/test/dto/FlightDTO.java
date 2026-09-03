package com.edu.test.dto;

import com.edu.test.enums.RequestAirline; 

import lombok.Data;

@Data
public class FlightDTO {

    private Integer flightId;
    private String flightName;
    private String source;
    private RequestAirline airline; 
    private String destination;
    private Integer totalSeats;
}