package com.FlightSearchApi.services.abstracts;

import com.FlightSearchApi.dto.requests.CreateFlightRequest;
import com.FlightSearchApi.dto.requests.UpdateFlightRequest;
import com.FlightSearchApi.dto.responses.GetAllFlightResponse;
import com.FlightSearchApi.exception.results.Result;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<GetAllFlightResponse> getAll();

    Result add(CreateFlightRequest createFlightRequests);

    Result update(UpdateFlightRequest updateFlightRequests);

    Result delete(Integer id);

    List<?> searchRoadTrip(String originAirport, String destinationAirport, LocalDate departureDate, LocalDate returnDate);
}
