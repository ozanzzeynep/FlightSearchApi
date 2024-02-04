package com.FlightSearchApi.repositories;

import com.FlightSearchApi.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer> {

    @Query(value = "select count(a) from Airport a where a.city = :city")
    int findAirportByCity(@Param("city") String city);

}
