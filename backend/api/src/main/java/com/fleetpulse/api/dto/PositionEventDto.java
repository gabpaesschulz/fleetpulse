package com.fleetpulse.api.dto;

import java.time.Instant;

public record PositionEventDto(String plate, double lat, double lng, Instant timestamp) {}
