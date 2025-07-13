package com.fleetpulse.api.controller;

import com.fleetpulse.api.dto.PositionEventDto;
import com.fleetpulse.api.dto.VehicleSummaryDto;
import com.fleetpulse.api.service.PositionEventService;
import com.fleetpulse.api.service.VehicleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PositionEventController {

    private final PositionEventService positionEventService;
    private final VehicleQueryService vehicleQueryService;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void receiveEvent(@RequestBody PositionEventDto dto) {
        positionEventService.saveEvent(dto);
    }

    @GetMapping("/vehicles")
    public List<VehicleSummaryDto> listVehicles() {
        return vehicleQueryService.fetchAll();
    }

    @GetMapping("/vehicles/{plate}")
    public List<PositionEventDto> history(
            @PathVariable String plate,
            @RequestParam(value = "since", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant since) {

        Instant start = since != null ? since : Instant.now().minus(1, ChronoUnit.DAYS);
        return positionEventService.history(plate, start);
    }
}
