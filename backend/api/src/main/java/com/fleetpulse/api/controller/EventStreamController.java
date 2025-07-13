package com.fleetpulse.api.controller;

import com.fleetpulse.api.dto.PositionEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
@RequiredArgsConstructor
public class EventStreamController {

    private final Sinks.Many<PositionEventDto> eventSink;

    @GetMapping(value = "/api/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PositionEventDto> stream() {
        return eventSink.asFlux();
    }
}
