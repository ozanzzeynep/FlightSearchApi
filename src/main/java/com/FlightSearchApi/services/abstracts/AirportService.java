package com.FlightSearchApi.services.abstracts;

import com.FlightSearchApi.dto.requests.CreateAirportRequest;
import com.FlightSearchApi.dto.requests.UpdateAirportRequest;
import com.FlightSearchApi.entities.Airport;
import com.FlightSearchApi.exception.results.Result;

import java.util.List;

public interface AirportService {

    List<Airport> getAll();

    Result add(CreateAirportRequest createAirportRequests);

    Result update(UpdateAirportRequest updateAirportRequests);

    Result delete(Integer id);

    void checkIsCityAlreadySaved(CreateAirportRequest createAirportRequest);

    void checkCityById(UpdateAirportRequest updateAirportRequest);

}
