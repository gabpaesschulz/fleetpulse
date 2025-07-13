package com.fleetpulse.api.service;

import com.fleetpulse.api.domain.PositionEvent;
import com.fleetpulse.api.domain.Vehicle;
import com.fleetpulse.api.dto.PositionEventDto;
import com.fleetpulse.api.repository.PositionEventRepository;
import com.fleetpulse.api.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PositionEventService {

    private final VehicleRepository vehicleRepo;
    private final PositionEventRepository eventRepo;
    private final Sinks.Many<PositionEventDto> eventSink;

    @Transactional
    public void saveEvent(PositionEventDto dto) {
        Vehicle v = vehicleRepo.findByPlate(dto.plate())
                .orElseThrow(() -> new IllegalArgumentException("Unknown plate"));
        PositionEvent ev = PositionEvent.builder()
                .vehicle(v).lat(dto.lat()).lng(dto.lng())
                .timestamp(dto.timestamp()).build();
        eventRepo.save(ev);

        eventSink.tryEmitNext(dto);
    }

    public List<PositionEventDto> history(String plate, Instant since) {
        Vehicle v = vehicleRepo.findByPlate(plate)
                .orElseThrow(() -> new IllegalArgumentException("Unknown plate"));
        return eventRepo.findByVehicleAndTimestampAfterOrderByTimestampAsc(v, since)
                .stream()
                .map(e -> new PositionEventDto(v.getPlate(), e.getLat(), e.getLng(), e.getTimestamp()))
                .toList();
    }
}
