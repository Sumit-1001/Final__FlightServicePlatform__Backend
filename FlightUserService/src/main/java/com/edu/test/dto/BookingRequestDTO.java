package com.edu.test.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {

    private Integer userId;

    private Integer scheduleId;

    private Integer numberOfSeats;
}