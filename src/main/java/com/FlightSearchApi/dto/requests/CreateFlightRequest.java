package com.FlightSearchApi.dto.requests;

import com.FlightSearchApi.entities.Airport;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CreateFlightRequest {

    private Airport originAirport;

    private Airport destinationAirport;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime departureDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime returnDate;

    private Double price;

}
