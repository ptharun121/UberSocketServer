package com.example.ubersocketserver.controllers;

import com.example.ubersocketserver.dto.RideRequestDto;
import com.example.ubersocketserver.dto.RideResponseDto;
import com.example.ubersocketserver.dto.TestResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/socket")
public class DriverRequestController {

    private SimpMessagingTemplate simpMessagingTemplate;

    public DriverRequestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/newride")
    public void raiseRaidRequest(@RequestBody RideRequestDto rideRequestDto) {
        sendDriversNewRideRequest(rideRequestDto);
    }

    public void sendDriversNewRideRequest(RideRequestDto rideRequestDto) {
        this.simpMessagingTemplate.convertAndSend("/topic/riderequest", rideRequestDto);
    }

    @MessageMapping("/rideresponse")
    public void rideResponseHandler(RideResponseDto rideResponseDto) {
        System.out.println(rideResponseDto.getResponse());
    }
}
