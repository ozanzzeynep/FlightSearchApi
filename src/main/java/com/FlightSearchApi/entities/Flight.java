package com.FlightSearchApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Flight extends BaseEntity{

    @ManyToOne()
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport originAirport;

    @ManyToOne()
    @JoinColumn(name = "destination_airport_id",nullable = false)
    private Airport destinationAirport;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "price")
    private Double price;
}
