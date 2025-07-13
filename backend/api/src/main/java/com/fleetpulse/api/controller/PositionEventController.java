package com.fleetpulse.api.controller;

import com.fleetpulse.api.dto.PositionEventDto;
import com.fleetpulse.api.dto.VehicleSummaryDto;
import com.fleetpulse.api.service.PositionEventService;
import com.fleetpulse.api.service.VehicleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PositionEventController {

    private final PositionEventService service;

    private final VehicleQueryService vehicleQueryService;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void receiveEvent(@RequestBody PositionEventDto dto) {
        service.saveEvent(dto);
    }

    @GetMapping("/vehicles")
    public List<VehicleSummaryDto> listVehicles() {
        return vehicleQueryService.fetchAll();
    }
}
