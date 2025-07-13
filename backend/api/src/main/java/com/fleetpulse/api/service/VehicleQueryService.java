package com.fleetpulse.api.service;

import com.fleetpulse.api.dto.VehicleSummaryDto;
import com.fleetpulse.api.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleQueryService {

    private final VehicleRepository repo;

    public List<VehicleSummaryDto> fetchAll() {
        return repo.findVehicleSummariesNative().stream()
                .map(row -> new VehicleSummaryDto(
                        (java.util.UUID) row[0],
                        (String) row[1],
                        (String) row[2],
                        (Double) row[3],
                        (Double) row[4],
                        (java.time.Instant) row[5]
                ))
                .toList();
    }
}
