package com.fleetpulse.api.service;

import com.fleetpulse.api.domain.PositionEvent;
import com.fleetpulse.api.domain.Vehicle;
import com.fleetpulse.api.dto.PositionEventDto;
import com.fleetpulse.api.repository.PositionEventRepository;
import com.fleetpulse.api.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PositionEventService {

    private final VehicleRepository vehicleRepo;
    private final PositionEventRepository eventRepo;

    @Transactional
    public void saveEvent(PositionEventDto dto) {
        Vehicle v = vehicleRepo.findByPlate(dto.plate())
                .orElseThrow(() -> new IllegalArgumentException("Unknown plate"));
        PositionEvent ev = PositionEvent.builder()
                .vehicle(v)
                .lat(dto.lat())
                .lng(dto.lng())
                .timestamp(dto.timestamp())
                .build();
        eventRepo.save(ev);
    }
}
