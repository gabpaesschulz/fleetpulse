package com.fleetpulse.api.repository;

import com.fleetpulse.api.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByPlate(String plate);

    @Query(value = """
    SELECT v.id, v.plate, v.driver_name,
           p.lat, p.lng, p.timestamp AS last_seen
      FROM vehicle v
 LEFT JOIN LATERAL (
         SELECT lat, lng, timestamp
           FROM position_event
          WHERE vehicle_id = v.id
          ORDER BY timestamp DESC
          LIMIT 1
      ) p ON TRUE
    ORDER BY v.plate
    """, nativeQuery = true)
    List<Object[]> findVehicleSummariesNative();
}
