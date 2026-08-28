package com.flight.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private Integer scheduleId;

    private Integer flightId;

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    private int availableSeats;

    private int totalSeats;

    private int price;
}