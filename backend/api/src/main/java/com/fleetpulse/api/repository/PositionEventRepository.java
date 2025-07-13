package com.fleetpulse.api.repository;

import com.fleetpulse.api.domain.PositionEvent;
import com.fleetpulse.api.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface PositionEventRepository extends JpaRepository<PositionEvent, UUID> {

    List<PositionEvent> findByVehicleAndTimestampAfterOrderByTimestampAsc(
            Vehicle vehicle, Instant since);
}
