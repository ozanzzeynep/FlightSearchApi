package com.FlightSearchApi.services.concretes;

import com.FlightSearchApi.dto.requests.CreateAirportRequest;
import com.FlightSearchApi.dto.requests.UpdateAirportRequest;
import com.FlightSearchApi.entities.Airport;
import com.FlightSearchApi.exception.results.Result;
import com.FlightSearchApi.exception.results.SuccessResult;
import com.FlightSearchApi.mapper.ModelMapperService;
import com.FlightSearchApi.repositories.AirportRepository;
import com.FlightSearchApi.services.abstracts.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirportManager implements AirportService {
    private final AirportRepository airportRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public List<Airport> getAll() {
        List<Airport> airports = airportRepository.findAll();
        if(airports.isEmpty()){
            new SuccessResult("No available flights");
        }
        return airports.stream().map(airport -> this.modelMapperService.forResponse()
                .map(airport, Airport.class)).collect(Collectors.toList());
    }

    @Override
    public Result add(CreateAirportRequest createAirportRequests) {
        checkIsCityAlreadySaved(createAirportRequests);
        Airport airport = this.modelMapperService.forRequest().map(createAirportRequests,Airport.class);
        airportRepository.save(airport);
        return new SuccessResult("Succesful");
    }

    @Override
    public Result update(UpdateAirportRequest updateAirportRequests) {
        checkCityById(updateAirportRequests);
        Airport airport = this.modelMapperService.forRequest().map(
                updateAirportRequests,Airport.class);
        airportRepository.save(airport);
        return new SuccessResult("Succesful");
    }

    @Override
    public Result delete(Integer id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if(airport.isEmpty()){
            throw new RuntimeException("Invalid id");
        }
        airportRepository.deleteById(id);
        return new SuccessResult("Succesful");
    }

    @Override
    public void checkIsCityAlreadySaved(CreateAirportRequest createAirportRequest) {
        int airportCountByCity = airportRepository.findAirportByCity(createAirportRequest.getCity());
        if(airportCountByCity > 0){
            throw new RuntimeException("Airport already saved.");
        }
    }

    @Override
    public void checkCityById(UpdateAirportRequest updateAirportRequest) {
        Optional<Airport> airport = airportRepository.findById(updateAirportRequest.getId());
        if(airport.isEmpty()){
            throw new RuntimeException("Invalid id");
        }
    }
}
