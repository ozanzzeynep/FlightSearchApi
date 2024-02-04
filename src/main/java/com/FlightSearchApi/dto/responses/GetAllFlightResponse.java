package com.FlightSearchApi.dto.responses;

import com.FlightSearchApi.entities.Airport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllFlightResponse {

    private Airport originAirport;

    private Airport destinationAirport;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime departureDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime returnDate;

    private Double price;

}
