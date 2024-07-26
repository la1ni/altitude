package com.project.altitude.repositories;

import com.project.altitude.domain.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
