package com.fleetpulse.api.config;

import com.fleetpulse.api.dto.PositionEventDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class EventStreamConfig {

    @Bean
    public Sinks.Many<PositionEventDto> eventSink() {
        return Sinks.many().replay().latest();
    }
}
