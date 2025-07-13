package com.fleetpulse.api;

import com.fleetpulse.api.domain.Vehicle;
import com.fleetpulse.api.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StartupData {

    private final VehicleRepository vehicleRepo;

    @Bean
    ApplicationRunner initData() {
        return args -> {
            if (vehicleRepo.count() == 0) {
                vehicleRepo.save(Vehicle.builder().plate("ABC-1234").driverName("Ana").build());
                vehicleRepo.save(Vehicle.builder().plate("DEF-5678").driverName("Bruno").build());
                vehicleRepo.save(Vehicle.builder().plate("GHI-9012").driverName("Carlos").build());
                vehicleRepo.save(Vehicle.builder().plate("JKL-3456").driverName("Diana").build());
                vehicleRepo.save(Vehicle.builder().plate("MNO-7890").driverName("Edu").build());
            }
        };
    }
}
