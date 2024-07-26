package com.project.altitude.domain.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false, name = "departue_time")
    private LocalDateTime departureTime;

    @Column(nullable = false, name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(nullable = false, name = "number_of_passengers")
    private int numberOfPassengers;
}
