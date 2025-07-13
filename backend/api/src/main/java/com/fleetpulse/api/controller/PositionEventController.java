package com.fleetpulse.api.controller;

import com.fleetpulse.api.dto.PositionEventDto;
import com.fleetpulse.api.service.PositionEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PositionEventController {

    private final PositionEventService service;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void receiveEvent(@RequestBody PositionEventDto dto) {
        service.saveEvent(dto);
    }
}
