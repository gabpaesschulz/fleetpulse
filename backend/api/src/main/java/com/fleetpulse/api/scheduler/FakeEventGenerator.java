package com.fleetpulse.api.scheduler;

import com.fleetpulse.api.dto.PositionEventDto;
import com.fleetpulse.api.service.PositionEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class FakeEventGenerator {

    private final PositionEventService service;
    private final Random random = new Random();

    // A cada 10 s
    @Scheduled(fixedRate = 10_000)
    public void sendFake() {
        // Gerar uma placa conhecida e posição aleatória entre SP e RJ
        String[] plates = {"ABC-1234","DEF-5678","GHI-9012","JKL-3456","MNO-7890"};
        String plate = plates[random.nextInt(plates.length)];
        double lat = -23.0 + random.nextDouble();  // simplificado
        double lng = -46.0 + random.nextDouble();

        service.saveEvent(new PositionEventDto(plate, lat, lng, Instant.now()));
    }
}
