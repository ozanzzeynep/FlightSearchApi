package com.FlightSearchApi.services.concretes;

import com.FlightSearchApi.entities.Airport;
import com.FlightSearchApi.entities.Flight;
import com.FlightSearchApi.repositories.AirportRepository;
import com.FlightSearchApi.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class ScheduledJobManager {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Scheduled(cron="0 0 12 * * ?")
    public void runMockData(){
        createFlight();
    }

    public void createFlight(){
        List<Airport> airports = airportRepository.findAll();


        for(int i = 0; i < 15; i++){
            Random randomFlight = new Random();
            int originAirportIndex = randomFlight.nextInt(airports.size());
            int destinationAirportIndex = randomFlight.nextInt(airports.size());

            while(originAirportIndex == destinationAirportIndex){
                destinationAirportIndex = randomFlight.nextInt(airports.size());
            }
            Airport originAirport = airports.get(originAirportIndex);
            Airport destinationAirport = airports.get(destinationAirportIndex);

            LocalDateTime departureD = LocalDateTime.now().plusDays(10);
            LocalDateTime returnD = departureD.plusDays(1);

            LocalDateTime departureDate = LocalDateTime.parse(departureD.format(formatter));
            LocalDateTime returnDate = LocalDateTime.parse(returnD.format(formatter));

            double price = randomFlight.nextDouble() * 5000;

            Flight flight1 = Flight.builder()
                    .originAirport(originAirport)
                    .destinationAirport(destinationAirport)
                    .departureDate(departureDate)
                    .returnDate(returnDate)
                    .price(price)
                    .build();
            flightRepository.save(flight1);

            Flight flight2 = Flight.builder()
                    .originAirport(destinationAirport)
                    .destinationAirport(originAirport)
                    .departureDate(returnDate)
                    .returnDate(departureDate)
                    .price(price)
                    .build();
            flightRepository.save(flight2);
        }
    }
}