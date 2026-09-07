package com.edu.test.dto;

import lombok.Data;

@Data
public class FlightDTO {

    private Integer flightId;
    private String flightName;
    private String source;
    private String destination;
    private Integer totalSeats;
}