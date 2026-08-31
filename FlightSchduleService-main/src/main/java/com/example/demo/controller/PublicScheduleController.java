package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FlightSchedule;
import com.example.demo.service.IFlightScheduleService;

@RestController
@RequestMapping("/api/public/schedules")
public class PublicScheduleController {

    @Autowired
    private IFlightScheduleService flightScheduleService;

    @GetMapping("/{id}")
    public FlightSchedule getScheduleById(
            @PathVariable int id) {

        return flightScheduleService.getScheduleById(id);
    }
}
