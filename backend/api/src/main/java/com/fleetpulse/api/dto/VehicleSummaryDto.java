package com.fleetpulse.api.dto;

import java.time.Instant;
import java.util.UUID;

public record VehicleSummaryDto(
        UUID id,
        String plate,
        String driverName,
        Double lat,
        Double lng,
        Instant lastSeen
) {}
