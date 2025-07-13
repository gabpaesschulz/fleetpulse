package com.fleetpulse.api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "position_event",
        indexes = @Index(columnList = "timestamp"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PositionEvent {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vehicle vehicle;

    private double lat;
    private double lng;

    private Instant timestamp;
}
