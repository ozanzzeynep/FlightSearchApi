package com.FlightSearchApi.services.concretes;

import com.FlightSearchApi.dto.requests.CreateFlightRequest;
import com.FlightSearchApi.dto.requests.UpdateFlightRequest;
import com.FlightSearchApi.dto.responses.GetAllFlightResponse;
import com.FlightSearchApi.dto.responses.GetOnewayFlightResponse;
import com.FlightSearchApi.entities.Flight;
import com.FlightSearchApi.exception.results.Result;
import com.FlightSearchApi.exception.results.SuccessResult;
import com.FlightSearchApi.mapper.ModelMapperService;
import com.FlightSearchApi.repositories.FlightRepository;
import com.FlightSearchApi.services.abstracts.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightManager  implements FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public List<GetAllFlightResponse> getAll() {
        List<Flight> flights = flightRepository.findAll();
        if(flights.isEmpty()){
            new SuccessResult("No available flights");
        }
        return flights.stream().map(flight -> this.modelMapperService.forResponse()
                .map(flight, GetAllFlightResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Result add(CreateFlightRequest createFlightRequests) {
        Flight flight = this.modelMapperService.forRequest()
                .map(createFlightRequests,Flight.class);
        flightRepository.save(flight);
        return new SuccessResult("Successful");
    }

    @Override
    public Result update(UpdateFlightRequest updateFlightRequests) {
        Flight flight = this.modelMapperService.forRequest()
                .map(updateFlightRequests,Flight.class);
        flightRepository.save(flight);
        return new SuccessResult("Successful");
    }

    @Override
    public Result delete(Integer id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isEmpty()){
            throw new RuntimeException("Flight is not found");
        }
        flightRepository.deleteById(id);
        return new SuccessResult("Successful");
    }

    @Override
    public List<?> searchRoadTrip(String originAirport, String destinationAirport, LocalDate departureDate, LocalDate returnDate) {
        if(returnDate == null){
            List<Flight> flights = flightRepository.searchOneWay(originAirport,destinationAirport,departureDate);
            return flights.stream().map(flight -> this.modelMapperService.forResponse()
                    .map(flight, GetOnewayFlightResponse.class)).collect(Collectors.toList());
        }
        List<Flight> flights = flightRepository.searchRoundTrip(originAirport,destinationAirport,departureDate,returnDate);
        return flights.stream().map(flight -> this.modelMapperService.forResponse()
                .map(flight, GetAllFlightResponse.class)).collect(Collectors.toList());
    }
}
