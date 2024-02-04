package com.FlightSearchApi.repositories;

import com.FlightSearchApi.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {

    @Query("select f from Flight f join f.originAirport originAirport join f.destinationAirport destinationAirport where originAirport.city = :originCity and" +
            " destinationAirport.city = :destinationCity and function('date', f.departureDate) = :departureDate")
    List<Flight> searchOneWay(@Param("originCity") String originAirport, @Param("destinationCity") String destinationAirport, @Param("departureDate") LocalDate departureDate);


    @Query("select f from Flight f join f.originAirport originAirport join f.destinationAirport destinationAirport where originAirport.city = :originCity and" +
            " destinationAirport.city = :destinationCity and function ('date', f.departureDate) = :departureDate and function ('date', f.returnDate) = :returnDate")
    List<Flight> searchRoundTrip(@Param("originCity") String originAirport, @Param("destinationCity") String destinationAirport
            , @Param("departureDate") LocalDate departureDate, @Param("returnDate")LocalDate returnDate);

}
